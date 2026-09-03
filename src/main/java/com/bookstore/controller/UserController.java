package com.bookstore.controller;

import com.bookstore.dto.UserCreateRequest;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.dto.UserResponse;
import com.bookstore.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	private final UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
		
	}
	 
	@GetMapping
	public List<UserResponse> findAllUsers(){
		return userService.findAllUsers();
	}
	
	@PostMapping   
	@ResponseStatus(HttpStatus.CREATED)
	public UserResponse create(@RequestBody UserCreateRequest request) {
		return userService.createUser(request);
	}
}
