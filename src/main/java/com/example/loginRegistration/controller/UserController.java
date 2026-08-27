package com.example.loginRegistration.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.loginRegistration.dto.Login;
import com.example.loginRegistration.dto.OtpRequest;
import com.example.loginRegistration.dto.EmailRequest;
import com.example.loginRegistration.dto.UpdateUserRequest;
import com.example.loginRegistration.dto.ChangePasswordRequest;
import com.example.loginRegistration.dto.ResetPasswordRequest;
import com.example.loginRegistration.dto.Registration;
import com.example.loginRegistration.dto.UserResponce;
import com.example.loginRegistration.service.UserService;
import com.example.loginRegistration.util.JWTUtil;

@CrossOrigin(origins = "*")
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

	@PutMapping("update/{id}")
	public ResponseEntity<UserResponce> updateUser(@PathVariable Long id, @RequestBody UpdateUserRequest req) {
		UserResponce updated = userService.updateUser(id, req);
		return ResponseEntity.ok(updated);
	}

	@PostMapping("/change-password")
	public ResponseEntity<String> changePassword(@RequestBody ChangePasswordRequest req) {
		return ResponseEntity.ok(userService.changePassword(req));
	}

	@PostMapping("/request-password-reset")
	public ResponseEntity<String> requestPasswordReset(@RequestBody EmailRequest req) {
		return ResponseEntity.ok(userService.requestPasswordReset(req.getEmail()));
	}

	@PostMapping("/reset-password")
	public ResponseEntity<String> resetPassword(@RequestBody ResetPasswordRequest req) {
		return ResponseEntity.ok(userService.resetPasswordWithOtp(req));
	}
	
	 @PostMapping("/verify")
	public ResponseEntity<?> verify(@RequestBody OtpRequest req) {
		return ResponseEntity.ok(userService.verifyOtp(req));
	}

		@PostMapping("/resend")
		public ResponseEntity<String> resendOtp(@RequestBody EmailRequest req) {
			return ResponseEntity.ok(userService.resendOtp(req.getEmail()));
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
