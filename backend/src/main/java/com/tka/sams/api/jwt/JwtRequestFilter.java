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
protected void doFilterInternal(
        HttpServletRequest request,
        HttpServletResponse response,
        FilterChain filterChain)
        throws ServletException, IOException {

    String authorizationHeader = request.getHeader("Authorization");

    String username = null;
    String jwt = null;

    if (authorizationHeader != null &&
            authorizationHeader.startsWith("Bearer ")) {

        jwt = authorizationHeader.substring(7);

        try {
            username = jwtUtil.extractUsername(jwt);

            System.out.println("JWT received");
            System.out.println("Username from JWT = " + username);

        } catch (Exception e) {
            System.out.println("Invalid JWT: " + e.getMessage());
        }
    }

    if (username != null &&
            SecurityContextHolder.getContext().getAuthentication() == null) {

        UserDetails userDetails =
                customUserDetailsService.loadUserByUsername(username);

        System.out.println("Username = " + userDetails.getUsername());
        System.out.println("Authorities = " + userDetails.getAuthorities());

        try {

            if (jwtUtil.validateToken(jwt, userDetails.getUsername())) {

                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities());

                authentication.setDetails(
                        new WebAuthenticationDetailsSource()
                                .buildDetails(request));

                SecurityContextHolder
                        .getContext()
                        .setAuthentication(authentication);

                System.out.println("Authentication stored successfully");
                System.out.println("FINAL AUTHORITIES = " +
                        SecurityContextHolder
                                .getContext()
                                .getAuthentication()
                                .getAuthorities());
            }

        } catch (Exception e) {
            System.out.println("JWT validation failed: " + e.getMessage());
        }
    }

    filterChain.doFilter(request, response);
}
}