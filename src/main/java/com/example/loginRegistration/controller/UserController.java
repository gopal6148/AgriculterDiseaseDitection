package com.example.loginRegistration.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.loginRegistration.dto.Login;
import com.example.loginRegistration.dto.Registration;
import com.example.loginRegistration.dto.UserResponce;
import com.example.loginRegistration.entity.User;
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
	private AuthenticationManager authentication;
	
	@PostMapping("/register")
	public ResponseEntity<String> saveUser(@RequestBody Registration registration) {
		 userService.saveUser(registration);
		 return ResponseEntity.ok("User register succefully");
	}
	
	@PostMapping("/login")
	public String login(@RequestBody Login login) {
		authentication.authenticate(
				new UsernamePasswordAuthenticationToken(login.getEmail(), login.getPassword())
				);
		return jwtUtil.genretedToken(login.getEmail());
	}
	
	@DeleteMapping("/{id}")
	public String deleteUser(@PathVariable Long id) {
		userService.deleteUser(id);
		return "delete succefully";
	}
	
    @GetMapping("/user")
	public List<UserResponce> getAllUser() {
		return userService.getAllUser();
	}
	
	@GetMapping("/message")
	public String getAll() {
		return "Hello";
	}

}
