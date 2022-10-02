package com.example.KeycloakAdminClientDemo.infrastructure.keycloak;

import lombok.RequiredArgsConstructor;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class KeycloakReader {

    private final Keycloak keycloak;

    @Value("${keycloak-realm}")
    private String realm;

    public boolean isExistsUserByEmail(String email) {
        var savedUserList = keycloak.realm(realm).users().search(email);

        return isExists(savedUserList);
    }

    private boolean isExists(List<UserRepresentation> savedUserList) {
        if (!savedUserList.isEmpty()) {
            return true;
        }

        return false;
    }

}
