package com.pack.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.pack.client.UAAClient;
import com.pack.dto.AuthenticationRequest;
import com.pack.dto.AuthenticationResponse;
import com.pack.dto.RegisterRequest;

import lombok.extern.slf4j.Slf4j;


@RestController
@Slf4j
public class RestClientController {

    @Autowired
    private UAAClient client;

    @PostMapping("/register")
    public String registerRequest(@RequestBody RegisterRequest req) {
        try {
            log.info("Register request sent: {}", req);
            String token = client.registerRequest(req);
            log.info("Response received: ", token);
        
            return token != null ? token : "Error: No token received";
        } catch (Exception e) {
            log.error("Error during registration request", e);
            return "Error: " + e.getMessage();
        }
    }
    
    @PostMapping("/authenticate")
    public String authenticateRequest(@RequestBody AuthenticationRequest req) {
        try {
            log.info("Authenticate request sent: {}", req);
            String token = client.authenticateRequest(req);
            log.info("Response received: ", token);
            
            return token != null ? token : "Error: No token received";
        } catch (Exception e) {
            log.error("Error during authentication request", e);
            return "Error: " + e.getMessage();
        }
    }
}


