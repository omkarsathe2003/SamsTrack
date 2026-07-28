package com.tka.sams.api.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Optional;

import com.tka.sams.api.repository.UserRepository;

import com.tka.sams.api.entity.User;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	@Autowired
	private UserRepository userRepository; 
   

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

    	Optional<User> optionalUser = userRepository.findByUsername(username);

    	if (!optionalUser.isPresent()) {
    	    throw new UsernameNotFoundException("User not found");
    	}

    	User user = optionalUser.get();

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRole().toUpperCase())
                .build();
    }
}