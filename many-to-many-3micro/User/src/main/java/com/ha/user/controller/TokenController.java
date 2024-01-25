package com.ha.testing.controller;

import lombok.RequiredArgsConstructor;
import org.keycloak.admin.client.Keycloak;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//@RestController
//@RequiredArgsConstructor
public class TokenController {

//    private final Keycloak keycloak;

//    @GetMapping("/protected-resource")
//    public ResponseEntity<String> getProtectedResource() {
//
//
//        // Use the access token for API calls to protected resources
//        // For example, make a request using RestTemplate with the token in the Authorization header.
//
//        return ResponseEntity.ok("Access Token: " + keycloak.tokenManager().getAccessToken().getToken());
//    }
}
