package com.tka.sams.api.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import java.util.Arrays;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import com.tka.sams.api.jwt.JwtRequestFilter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

	@Autowired
	private JwtRequestFilter jwtRequestFilter;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http
	    .cors()
	    .and()
	    .csrf().disable()

	    .authorizeRequests()

	    // Public API
	    .antMatchers("/authenticate").permitAll()

	    // Logged in users
	    .antMatchers("/user/me").authenticated()

	    // Admin only
	    .antMatchers(
	            "/user/register-user",
	            "/user/delete-user-by-username",
	            "/user/update-user",
	            "/user/get-all-user",
	            "/user/get-all-admin",
	            "/user/get-all-faculty"
	    ).hasRole("ADMIN")
	    .antMatchers(
	            "/student/get-all-students",
	            "/student/get-student-by-id/**",
	            "/student/add-student",
	            "/student/update-student"
	    ).hasAnyRole("ADMIN", "FACULTY")

	    .antMatchers(
	            "/student/delete-student/**"
	    ).hasRole("ADMIN")
	    // Everything else requires login
	    .anyRequest().authenticated()

	    .and()

	    .sessionManagement()
	    .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

	http.addFilterBefore(jwtRequestFilter,
	        UsernamePasswordAuthenticationFilter.class);

	return http.build();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {

	    CorsConfiguration configuration = new CorsConfiguration();

	    configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
	    configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
	    configuration.setAllowedHeaders(Arrays.asList("*"));
	    configuration.setAllowCredentials(true);

	    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	    source.registerCorsConfiguration("/**", configuration);

	    return source;
	}
}