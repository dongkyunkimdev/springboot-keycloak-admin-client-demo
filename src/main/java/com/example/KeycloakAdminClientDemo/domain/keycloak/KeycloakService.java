package com.example.KeycloakAdminClientDemo.domain.keycloak;

import com.example.KeycloakAdminClientDemo.infrastructure.keycloak.KeycloakStore;
import com.example.KeycloakAdminClientDemo.interfaces.keycloak.KeycloakDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class KeycloakService {

    private final KeycloakValidator keycloakValidator;
    private final KeycloakStore keycloakStore;

    @Transactional
    public void signup(KeycloakDto.SignupRequest request) {
        keycloakValidator.checkDuplicateEmail(request.getEmail());

        keycloakStore.store(request);
    }

}
