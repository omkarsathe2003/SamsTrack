package com.tka.sams.api.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.tka.sams.api.security.CustomUserDetailsService;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String authorizationHeader = request.getHeader("Authorization");

		String username = null;
		String jwt = null;

		// Check if JWT is present
		if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {

			jwt = authorizationHeader.substring(7);

			username = jwtUtil.extractUsername(jwt);
		}

		// Authenticate user if not already authenticated
		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

			UserDetails userDetails =
			        customUserDetailsService.loadUserByUsername(username);

			System.out.println("Username = " + userDetails.getUsername());
			System.out.println("Authorities = " + userDetails.getAuthorities());
			if (jwtUtil.validateToken(jwt, userDetails.getUsername())) {

				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());

				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

				SecurityContextHolder.getContext().setAuthentication(authentication);
				System.out.println("Authentication stored successfully");
			}
		}

		filterChain.doFilter(request, response);
	}
}