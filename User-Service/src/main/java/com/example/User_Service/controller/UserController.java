package com.example.User_Service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.User_Service.dtos.LoginRequest;
import com.example.User_Service.dtos.RegisterRequest;
import com.example.User_Service.dtos.UserResponse;
import com.example.User_Service.service.User_Service;

@RestController
@RequestMapping("/api/user")
public class UserController {
	@Autowired
	private User_Service userService;
	
	@PostMapping("/login")
	public ResponseEntity<UserResponse> login(@RequestBody LoginRequest loginRequest) {
		return ResponseEntity.ok(userService.login(loginRequest));
	}
	
	
	@PostMapping("/register")
	public UserResponse registrationUser(@RequestBody RegisterRequest registrationRequest) {
		return userService.registrationUser(registrationRequest);
	}

}
