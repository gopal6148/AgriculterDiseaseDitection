package com.example.User_Service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.User_Service.dtos.LoginRequest;
import com.example.User_Service.dtos.RegisterRequest;
import com.example.User_Service.service.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	private UserService us;
	
	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
		return ResponseEntity.ok(us.registerUser(request));
	}
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginRequest request){
		return ResponseEntity.ok(us.authenticate(request));
	}

}
