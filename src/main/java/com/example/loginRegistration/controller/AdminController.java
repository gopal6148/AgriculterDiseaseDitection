package com.example.loginRegistration.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.loginRegistration.entity.User;
import com.example.loginRegistration.service.UserService;

@RestController
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private UserService userService;
	
	@DeleteMapping("/deleteUser/{id}")
	public String deleteUser(@PathVariable long id) {
		return userService.deleteUser(id);
	}
	
	@GetMapping("allUser")
	public List<User> getAll() {
		return userService.getAllUser();
	}

}
