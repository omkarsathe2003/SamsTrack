package com.tka.sams.api.controller;

import java.util.List;
import com.tka.sams.api.dto.UserResponse;
import com.tka.sams.api.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.tka.sams.api.model.LoginRequest;
import com.tka.sams.api.service.UserService;
import com.tka.sams.api.mapper.UserMapper;

@RestController
@RequestMapping("/user")
@CrossOrigin("http://localhost:4200")
public class UserController {

	@Autowired
	private UserService service;
	
	
// http://localhost:8091/user/login-user
	@GetMapping("/get-user-by-username/{username}")
	public UserResponse getUserById(@PathVariable String username) {

	    User user = service.getUserByName(username);

	    return UserMapper.toResponse(user);
	}

	@CrossOrigin(methods = RequestMethod.POST)
	@PostMapping("/register-user")
	public ResponseEntity<String> registerUser(@RequestBody User user) {
		User registerUser = service.registerUser(user);
		if (registerUser != null) {
			return new ResponseEntity<String>("Registered", HttpStatus.CREATED);
		} else {
			return new ResponseEntity<String>("Something Went Wrong", HttpStatus.OK);
		}
	}


	@GetMapping("/get-all-user")
	public List<UserResponse> getAllUser() {

	    return service.getAllUser()
	            .stream()
	            .map(UserMapper::toResponse)
	            .toList();
	}
	
	@GetMapping("/get-all-admin")
	public List<UserResponse> getAllAdmins() {

	    return service.getAllAdmins()
	            .stream()
	            .map(UserMapper::toResponse)
	            .toList();
	}
	
	@GetMapping("/get-all-faculty")
	public List<UserResponse> getAllFaculties() {

	    return service.getAllFaculties()
	            .stream()
	            .map(UserMapper::toResponse)
	            .toList();
	}

	//localhost:8091/user/delete-user-by-username?username=ram
	
	@DeleteMapping("/delete-user-by-username")
	public String deleteUserById(@RequestParam String username) {
		return service.deleteUserById(username);
	}

	@PutMapping("/update-user")
	public User updateUser(@RequestBody User user) {
		return service.updateUser(user);
	}
	
	@GetMapping("/me")
	public UserResponse getLoggedInUser() {

	    Authentication authentication =
	            SecurityContextHolder.getContext().getAuthentication();
	   
	    System.out.println("Controller reached");
	    System.out.println(authentication);
	    String username = authentication.getName();

	    User user = service.getUserByName(username);

	    return UserMapper.toResponse(user);
	}

}
