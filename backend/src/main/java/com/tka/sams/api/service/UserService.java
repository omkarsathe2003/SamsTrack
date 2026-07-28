package com.tka.sams.api.service;

import java.net.PasswordAuthentication;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tka.sams.api.dao.UserDao;
import com.tka.sams.api.entity.User;
import com.tka.sams.api.exceptions.ResourceNotFoundException;
import com.tka.sams.api.model.LoginRequest;

@Service
public class UserService {
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserDao dao;

	public User loginUser(LoginRequest request) {
		return dao.loginUser(request);
	}

	public User registerUser(User user) {
		
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		
		return dao.registerUser(user);
	}

	public User getUserByName(String username) {

	    User user = dao.getUserByName(username);

	    if (user == null) {
	        throw new ResourceNotFoundException(
	                "User not found : " + username);
	    }

	    return user;
	}

	public List<User> getAllUser() {
		return dao.getAllUser();
	}

	public User updateUser(User user) {

	    User existingUser = dao.getUserByName(user.getUsername());

	    if (existingUser == null) {
	        throw new ResourceNotFoundException(
	                "User not found : " + user.getUsername());
	    }

	    return dao.updateUser(user);
	}

	public String deleteUserById(String username) {

	    User user = dao.getUserByName(username);

	    if (user == null) {
	        throw new ResourceNotFoundException(
	                "User not found : " + username);
	    }

	    return dao.deleteUserById(username);
	}

	public List<User> getAllAdmins() {
		return dao.getAllAdmins();
	}
	
	public List<User> getAllFaculties() {
		return dao.getAllFaculties();
	}

}
