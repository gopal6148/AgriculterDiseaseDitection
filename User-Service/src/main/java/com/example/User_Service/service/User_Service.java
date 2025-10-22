package com.example.User_Service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.User_Service.dtos.LoginRequest;
import com.example.User_Service.dtos.RegisterRequest;
import com.example.User_Service.entity.User;
import com.example.User_Service.repository.UserRepository;

@Service
public class User_Service {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public User login(LoginRequest loginRequest) {
			User  user = userRepository.findByEmail(loginRequest.getEmail());
			if(user == null) {
				throw new RuntimeException("User not found");
			}
		if(!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
			throw new RuntimeException("Invalid password");

        }
		return user; 
		
	}
	
	public User registrationUser(RegisterRequest registrationRequest) {
		if (userRepository.existsByEmail(registrationRequest.getEmail())) {
            throw new RuntimeException("User already exists with this email");
        }
		User user = new User();
		user.setUsername(registrationRequest.getUsername());
		user.setEmail(registrationRequest.getEmail());
		user.setMobileNum(registrationRequest.getMobileNum());
		user.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
		return userRepository.save(user);
	}
	  
}
