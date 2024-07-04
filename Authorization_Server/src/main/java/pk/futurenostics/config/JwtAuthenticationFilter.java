package pk.futurenostics.config;

import java.io.IOException;

import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter{
	
	private final JwtService jwtService;
	private final UserDetailsService userDetailsService;

	@Override
	protected void doFilterInternal(
		@NonNull HttpServletRequest request, //our request
		@NonNull HttpServletResponse response,// out response
			     FilterChain filterChain // contain the list of filters that we need to  execute
			)
			throws ServletException, IOException {
		
		// Check if we have a token
		final String authHeader = request.getHeader("Authorization");// having Bearer token
		final String jwt;
		final String userEmail;
		
		if(authHeader == null || authHeader.startsWith("Bearer ")) {
			filterChain.doFilter(request, response);
			return;
		}
		
	    // Now getting jwt token from authHeader
		jwt = authHeader.substring(7);
		
		// Extracting userEmail from jwt token
		userEmail = jwtService.extractUsername(jwt);
		
		if(userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
			
			if(jwtService.isTokenValid(jwt, userDetails)) {
				UsernamePasswordAuthenticationToken authToken = 
						new UsernamePasswordAuthenticationToken(
							userDetails,
							null,
							userDetails.getAuthorities()
								);
				
				authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				// Update Security context Holder
				SecurityContextHolder.getContext().setAuthentication(authToken);
						
			}
		}
		
		filterChain.doFilter(request, response);
		
	}
	
	
	

}
