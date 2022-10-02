package com.example.KeycloakAdminClientDemo.domain.keycloak;

import com.example.KeycloakAdminClientDemo.infrastructure.keycloak.KeycloakReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KeycloakValidator {

    private final KeycloakReader keycloakReader;

    public void checkDuplicateEmail(String email) {
        if (keycloakReader.isExistsUserByEmail(email)) {
            throw new IllegalArgumentException("email already exists");
        }
    }

}
