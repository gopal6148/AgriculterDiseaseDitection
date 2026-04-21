package com.example.loginRegistration.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.loginRegistration.dto.Registration;
import com.example.loginRegistration.enm.Role;
import com.example.loginRegistration.entity.User;
import com.example.loginRegistration.repository.UserRepo;

@Service
public class UserService {
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
    private PasswordEncoder passwordEncoder;

	
	public String saveUser(Registration regist) {
		if(userRepo.existsByEmail(regist.getEmail())) {
			return "Email already exists";
		}
		if(userRepo.existsByMobileNum(regist.getMobileNum())) {
			return "mobile number alredy exists";
		}
		
		User user = new User();
		user.setFname(regist.getFname());
		user.setEmail(regist.getEmail());
		user.setMobileNum(regist.getMobileNum());
		user.setPassword(passwordEncoder.encode(regist.getPassword()));
		user.setRole(Role.USER);
		user.setLocalDateTime(LocalDateTime.now());
		userRepo.save(user);
		
		return "user register successfully";
	}

}
