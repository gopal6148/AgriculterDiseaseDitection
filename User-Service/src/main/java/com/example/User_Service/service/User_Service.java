package com.example.User_Service.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.User_Service.dtos.LoginRequest;
import com.example.User_Service.dtos.RegisterRequest;
import com.example.User_Service.dtos.UserResponse;
import com.example.User_Service.entity.User;
import com.example.User_Service.exception.InvalidCredentialsException;
import com.example.User_Service.exception.UserAlreadyExistsException;
import com.example.User_Service.exception.UserNotFoundException;
import com.example.User_Service.repository.UserRepository;

@Service
public class User_Service {
	
	private static final Logger logger = LoggerFactory.getLogger(User_Service.class);
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public UserResponse login(LoginRequest loginRequest) {
		if(loginRequest.getEmail() == null || loginRequest.getEmail().isEmpty()) {
			throw new IllegalArgumentException("Email can not be empty");
		}
		if(loginRequest.getPassword() == null || loginRequest.getPassword().isEmpty()) {
			throw new IllegalArgumentException("password can not be empty");
		}
		
			User  user = userRepository.findByEmail(loginRequest.getEmail());
			if(user == null) {
				throw new UserNotFoundException("User not found with this email");
			}
		if(!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
			throw new InvalidCredentialsException("Invalid password");

        }
		logger.info("User logged in: {}", user.getEmail());
		return new UserResponse(user.getEmail(),user.getUsername(),user.getMobileNum()); 
		
	}
	
	public UserResponse registrationUser(RegisterRequest registrationRequest) {
		if (userRepository.existsByEmail(registrationRequest.getEmail())) {
            throw new UserAlreadyExistsException("User already exists with this email");
        }
		User user = new User();
		user.setUsername(registrationRequest.getUsername());
		user.setEmail(registrationRequest.getEmail());
		user.setMobileNum(registrationRequest.getMobileNum());
		user.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
		userRepository.save(user);
		logger.info("User trying to register: {}", registrationRequest.getEmail());
		return new UserResponse(user.getEmail(),user.getUsername(),user.getMobileNum());
	}
	  
}
