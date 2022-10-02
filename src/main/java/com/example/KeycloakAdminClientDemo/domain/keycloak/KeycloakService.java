package com.example.KeycloakAdminClientDemo.domain.keycloak;

import com.example.KeycloakAdminClientDemo.infrastructure.keycloak.KeycloakReader;
import com.example.KeycloakAdminClientDemo.infrastructure.keycloak.KeycloakStore;
import com.example.KeycloakAdminClientDemo.interfaces.keycloak.KeycloakDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class KeycloakService {

    private final KeycloakValidator keycloakValidator;
    private final KeycloakReader keycloakReader;
    private final KeycloakStore keycloakStore;

    @Transactional
    public void signup(KeycloakDto.SignupRequest request) {
        keycloakValidator.checkDuplicateEmail(request.getEmail());

        keycloakStore.store(request);
    }

    @Transactional
    public void sendMailVerifyEmail(KeycloakDto.EmailRequest request) {
        var userId = keycloakReader.getUserId(request.getEmail());

        keycloakStore.sendMailVerifyEmail(userId);
    }

    @Transactional
    public void sendMailUpdatePassword(KeycloakDto.EmailRequest request) {
        var userId = keycloakReader.getUserId(request.getEmail());

        keycloakStore.sendMailUpdatePassword(userId);
    }

}
