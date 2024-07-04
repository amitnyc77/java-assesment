package com.pack.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pack.client.AuthClient;
import com.pack.dto.AuthenticationRequest;
import com.pack.dto.AuthenticationResponse;
import com.pack.dto.RegisterRequest;

import lombok.extern.slf4j.Slf4j;


@RestController
@RequestMapping("/auth")
@Slf4j
public class UAAController {

    @Autowired
    private AuthClient client;

    @PostMapping("/register")
    public String registerRequest(@RequestBody RegisterRequest req) {
        try {
            log.info("Register request sent: {}", req);
            ResponseEntity<AuthenticationResponse> token = client.register(req);
            log.info("Response received: {}", token);
            AuthenticationResponse response = token.getBody();
            return response != null ? response.getToken() : "Error: No token received";
        } catch (Exception e) {
            log.error("Error during registration request", e);
            return "Error: " + e.getMessage();
        }
    }
    
    @PostMapping("/authenticate")
    public String authenticateRequest(@RequestBody AuthenticationRequest req) {
        try {
            log.info("Authenticate request sent: {}", req);
            ResponseEntity<AuthenticationResponse> token = client.authenticate(req);
            log.info("Response received: {}", token);
            AuthenticationResponse response = token.getBody();
            return response != null ? response.getToken() : "Error: No token received";
        } catch (Exception e) {
            log.error("Error during authentication request", e);
            return "Error: " + e.getMessage();
        }
    }
}


