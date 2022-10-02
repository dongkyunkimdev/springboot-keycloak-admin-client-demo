package com.example.KeycloakAdminClientDemo.infrastructure.keycloak;

import com.example.KeycloakAdminClientDemo.interfaces.keycloak.KeycloakDto;
import lombok.RequiredArgsConstructor;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.ws.rs.core.Response;
import java.util.List;

@Component
@RequiredArgsConstructor
public class KeycloakStore {

    private final Keycloak keycloak;

    @Value("${keycloak-realm}")
    private String realm;

    public void store(KeycloakDto.SignupRequest request) {
        var password = preparePasswordRepresentation(request.getPassword());
        var user = prepareUserRepresentation(request, password);

        Response response = keycloak.realm(realm).users().create(user);

        validateCreate(response);
    }

    public void sendMailVerifyEmail(String userId) {
        keycloak
                .realm(realm)
                .users()
                .get(userId)
                .executeActionsEmail(List.of("VERIFY_EMAIL"));
    }

    private void validateCreate(Response response) {
        if (response.getStatus() != HttpStatus.CREATED.value()) {
            throw new RuntimeException("failed to create user");
        }
    }

    private CredentialRepresentation preparePasswordRepresentation(String password) {
        var cR = new CredentialRepresentation();

        cR.setTemporary(false);
        cR.setType(CredentialRepresentation.PASSWORD);
        cR.setValue(password);

        return cR;
    }

    private UserRepresentation prepareUserRepresentation(KeycloakDto.SignupRequest request, CredentialRepresentation cR) {
        var newUser = new UserRepresentation();

        newUser.setEmail(request.getEmail());
        newUser.setFirstName(request.getFirstName());
        newUser.setLastName(request.getLastName());
        newUser.setCredentials(List.of(cR));
        newUser.setEnabled(true);

        return newUser;
    }

}
