package pk.futurenostics.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import pk.futurenostics.config.JwtService;
import pk.futurenostics.controller.AuthenticationRequest;
import pk.futurenostics.controller.AuthenticationResponse;
import pk.futurenostics.controller.RegisterRequest;
import pk.futurenostics.dao.UserRepo;
import pk.futurenostics.user.Role;
import pk.futurenostics.user.User;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
	private final UserRepo userRepo;
	private final PasswordEncoder passwordEncoder;
	private final JwtService jwtService;
	private final AuthenticationManager authenticationManager;
	
	
	
	public AuthenticationResponse register(RegisterRequest request) {
		var user = User.builder()
				.fname(request.getFname())
				.lname(request.getLname())
				.email(request.getEmail())
				.password(passwordEncoder.encode(request.getPassword()))
				 .role(Role.USER)
				.build();
		System.out.println("AuthenticationService.register()");
		userRepo.save(user);
		var jwtToken = jwtService.generateToken(user);
		System.out.println(jwtToken);
		return AuthenticationResponse
				.builder()
				.token(jwtToken).build();
	}

	public AuthenticationResponse authenticate(AuthenticationRequest request) {
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						request.getEmail(), request.getPassword()));
		System.out.println("AuthenticationService.authenticate()");
		
		var user = userRepo.findByEmail(request.getEmail());
		
		var jwtToken = jwtService.generateToken(user.get());
      return  AuthenticationResponse
				.builder()
				.token(jwtToken).build();
	}
	


}




















