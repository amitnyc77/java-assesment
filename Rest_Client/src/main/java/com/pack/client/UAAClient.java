package com.pack.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.pack.dto.AuthenticationRequest;
import com.pack.dto.AuthenticationResponse;
import com.pack.dto.RegisterRequest;

@FeignClient(name = "UAA-SERVICE")
public interface UAAClient {
	
	@PostMapping("/auth/register")
	public String registerRequest(@RequestBody RegisterRequest req);

	@PostMapping("/auth/authenticate")
	public String authenticateRequest(AuthenticationRequest req);
}
