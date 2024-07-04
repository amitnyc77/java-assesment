package com.pack.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.pack.dto.AuthenticationRequest;
import com.pack.dto.AuthenticationResponse;
import com.pack.dto.RegisterRequest;

@FeignClient(name = "JWT-SERVICE")
public interface AuthClient {
	
	@PostMapping("/api/v1/auth/register")
	public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest req);

	@PostMapping("/api/v1/auth/authenticate")
	public ResponseEntity<AuthenticationResponse> authenticate(AuthenticationRequest req);
}
