package com.example.loginRegistration.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.loginRegistration.dto.Login;
import com.example.loginRegistration.dto.Registration;
import com.example.loginRegistration.service.UserService;
import com.example.loginRegistration.util.JWTUtil;

@RestController
@RequestMapping("/auth")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private JWTUtil jwtUtil;
	
	@Autowired
	AuthenticationManager authentication;
	
	@PostMapping("/register")
	public String saveUser(@RequestBody Registration registration) {
		return userService.saveUser(registration);
	}
	
	@PostMapping("/login")
	public String login(@RequestBody Login login) {
		return userService.login(login);
	}
	
	@PostMapping("/login2")
	public String login2(@RequestBody Login login) {
		authentication.authenticate(
				new UsernamePasswordAuthenticationToken(login.getEmail(), login.getPassword())
				);
		
		return jwtUtil.genretedToken(login.getEmail());
	}
	
	@GetMapping("/massage")
	public String getAll() {
		return "Hello";
	}

}
