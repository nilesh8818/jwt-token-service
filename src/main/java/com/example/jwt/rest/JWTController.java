package com.example.jwt.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.jwt.entity.JWTRequest;
import com.example.jwt.entity.JWTResponse;
import com.example.jwt.service.CustomeUserDetailsService;
import com.example.jwt.util.JwtUtil;

@RestController
public class JWTController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private CustomeUserDetailsService customeUserDetailsService;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@PostMapping("/token")
	public ResponseEntity<?> getToken(@RequestBody JWTRequest jwtRequest) {
		System.out.println(jwtRequest);
		
		try {
		this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUserName(), jwtRequest.getUserPassword()));
		}catch (UsernameNotFoundException e) {
			e.printStackTrace();
			throw new UsernameNotFoundException("Bad Credetials.....!!!!!");
		}
		
		UserDetails userDetails=customeUserDetailsService.loadUserByUsername(jwtRequest.getUserName());
		String token=jwtUtil.generateToken(userDetails);
		System.out.println("Genrated token is::"+token);
		
		return ResponseEntity.ok(new JWTResponse(token));
	}
	
}
