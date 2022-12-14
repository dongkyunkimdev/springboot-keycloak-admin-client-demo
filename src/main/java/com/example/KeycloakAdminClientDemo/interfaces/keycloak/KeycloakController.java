package com.example.KeycloakAdminClientDemo.interfaces.keycloak;

import com.example.KeycloakAdminClientDemo.domain.keycloak.KeycloakService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class KeycloakController {

    private final KeycloakService keycloakService;

    @PostMapping("/signup")
    public ResponseEntity signup(@RequestBody @Valid KeycloakDto.SignupRequest request) {
        keycloakService.signup(request);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/send-mail-verify-email")
    public ResponseEntity sendMailVerifyEmail(@RequestBody @Valid KeycloakDto.EmailRequest request) {
        keycloakService.sendMailVerifyEmail(request);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/send-mail-update-password")
    public ResponseEntity sendMailUpdatePassword(@RequestBody @Valid KeycloakDto.EmailRequest request) {
        keycloakService.sendMailUpdatePassword(request);

        return ResponseEntity.ok().build();
    }

}
