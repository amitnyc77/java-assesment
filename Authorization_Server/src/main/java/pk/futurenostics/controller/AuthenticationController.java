package pk.futurenostics.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import pk.futurenostics.service.AuthenticationService;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
	
	private final AuthenticationService authenticationService;
	
	@PostMapping("/register")
	public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request){
		AuthenticationResponse token=  authenticationService.register(request);
		//return ResponseEntity.ok(authenticationService.register(request));
		
		return new ResponseEntity<AuthenticationResponse>(token,HttpStatus.OK);
		
	}
	
	@PostMapping("/authenticate")
	public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request){
		AuthenticationResponse token =authenticationService.authenticate(request);
		
		//return ResponseEntity.ok(authenticationService.authenticate(request));

				return new ResponseEntity<AuthenticationResponse>(token,HttpStatus.OK);
	}

}













