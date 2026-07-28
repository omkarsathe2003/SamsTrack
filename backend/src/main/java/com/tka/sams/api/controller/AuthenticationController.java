package com.tka.sams.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tka.sams.api.jwt.JwtUtil;
import com.tka.sams.api.model.AuthenticationResponse;
import com.tka.sams.api.model.LoginRequest;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class AuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtil jwtUtil;

	@PostMapping("/authenticate")
	public AuthenticationResponse authenticate(@RequestBody LoginRequest request) {

		authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		String token = jwtUtil.generateToken(request.getUsername());
		return new AuthenticationResponse(token);
	}
}