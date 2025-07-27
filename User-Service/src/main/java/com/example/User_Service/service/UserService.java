package com.example.User_Service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.User_Service.dtos.*;
import com.example.User_Service.entity.User;
import com.example.User_Service.repository.*;

@Service
public class UserService {
	@Autowired
	private UserRepository ur;
	
	@Autowired
	private PasswordEncoder pe;
	
	
	 public User registerUser(RegisterRequest request) {
	        User user = new User();
	        user.setEmail(request.getEmail());
	        user.setUsername(request.getUsername());
	        user.setMobileNum(request.getMobileNum());
	        user.setPassword(pe.encode(request.getPassword()));
	        return ur.save(user);
	    }
	 public User authenticate(LoginRequest request) {
	        User user = ur.findByEmail(request.getEmail());
	           
	        if (!pe.matches(request.getPassword(), user.getPassword())) {
	            throw new RuntimeException("Invalid password");
	        }
	        return user;
	    }
	
	

}
