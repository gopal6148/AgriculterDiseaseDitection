package com.example.loginRegistration.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;  
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.loginRegistration.dto.OtpRequest;
import com.example.loginRegistration.dto.Registration;
import com.example.loginRegistration.dto.UpdateUserRequest;
import com.example.loginRegistration.dto.ChangePasswordRequest;
import com.example.loginRegistration.dto.ResetPasswordRequest;
import com.example.loginRegistration.dto.UserResponce;
import com.example.loginRegistration.enm.Role;
import com.example.loginRegistration.entity.User;
import com.example.loginRegistration.exceptionHandling.MobileNumberAllreadyExist;
import com.example.loginRegistration.exceptionHandling.UserAllRedyRegister;
import com.example.loginRegistration.repository.UserRepo;

@Service
public class UserService implements UserDetailsService{

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private EmailService emailService;


	public String saveUser(Registration regist) {
		if(userRepo.existsByEmail(regist.getEmail())) {
			throw new UserAllRedyRegister("email all ready exist");
		}
		if(userRepo.existsByMobileNum(regist.getMobileNum())) {
			throw new MobileNumberAllreadyExist("mobile number all ready exist");
		}

		User user = new User();
		user.setFname(regist.getFname());
		user.setEmail(regist.getEmail());
		user.setMobileNum(regist.getMobileNum());
		user.setPassword(passwordEncoder.encode(regist.getPassword()));
		// Set default role to USER for newly registered users
		user.setRole(Role.USER);

		String otp = generateOtp();
		user.setOtp(otp);
		user.setOtpExpriresAt(LocalDateTime.now().plusMinutes(5));

		user.setLocalDateTime(LocalDateTime.now());
		userRepo.save(user);

		emailService.sendOtp(user.getEmail(), otp);

		return "send otp";
	}

	private String generateOtp() {

		return String.valueOf(new Random().nextInt(900000) + 100000);
	}

	public String verifyOtp(OtpRequest req) {

		User user = userRepo.findByEmail(req.getEmail())
				.orElseThrow(() -> new RuntimeException("Invalid email"));

		if (req.getOtp() == null) {
			throw new RuntimeException("OTP is required");
		}

		if (!req.getOtp().equals(user.getOtp())) {
			throw new RuntimeException("Invalid OTP");
		}

		if (user.getOtpExpriresAt().isBefore(LocalDateTime.now())) {
			throw new RuntimeException("OTP expired");
		}

		user.setOtpVerified(true);
		user.setOtp(null);
		user.setOtpExpriresAt(null);

		userRepo.save(user);

		return "Email Verified successfully";
	}

	public String resendOtp(String email) {
		User user = userRepo.findByEmail(email)
				.orElseThrow(() -> new RuntimeException("Invalid email"));

		String otp = generateOtp();
		user.setOtp(otp);
		user.setOtpExpriresAt(LocalDateTime.now().plusMinutes(5));

		userRepo.save(user);

		emailService.sendOtp(user.getEmail(), otp);

		return "OTP resent successfully";
	}

	/**public String login(Login login) {
		if(login.getEmail() == null || login.getEmail().isEmpty()) {
			throw new IllegalArgumentException("email is not empty");
		}
		if(login.getPassword() == null || login.getPassword().isEmpty()) {
			throw new IllegalArgumentException("password is not empty");
		}

		User user = userRepo.findByEmail(login.getEmail())
				.orElseThrow(()-> new RuntimeException("invalid email"));

		if(!passwordEncoder.matches(login.getPassword(), user.getPassword())) {
			throw new PasswordNotFound("invalid password");
		}

		return "login successfully";
	}**/

	@Override
	public UserDetails loadUserByUsername(String email) {
		User user = userRepo.findByEmail(email)
				.orElseThrow(() -> new RuntimeException("Invalid email"));
		return new org.springframework.security.core.userdetails.User(
				user.getEmail(),
				user.getPassword(),
				List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole().name()))
				);
	}

	// Update user profile
	@PreAuthorize("hasAuthority('USER_UPDATE') or hasRole('ADMIN')")
	public UserResponce updateUser(long id, UpdateUserRequest req) {
		User user = userRepo.findById(id).orElseThrow(() -> new RuntimeException("Invalid email"));
		if (!user.getMobileNum().equals(req.getMobileNum()) && userRepo.existsByMobileNum(req.getMobileNum())) {
			throw new MobileNumberAllreadyExist("mobile number already exist");
		}

		user.setFname(req.getFname());
		user.setMobileNum(req.getMobileNum());
		userRepo.save(user);

		return new UserResponce(user.getId(), user.getFname(), user.getEmail(), user.getMobileNum(), user.getRole(), user.getLocalDateTime());
	}

	// Change password using current password
	public String changePassword(ChangePasswordRequest req) {
		User user = userRepo.findByEmail(req.getEmail()).orElseThrow(() -> new RuntimeException("Invalid email"));

		if (!passwordEncoder.matches(req.getCurrentPassword(), user.getPassword())) {
			throw new com.example.loginRegistration.exceptionHandling.PasswordNotFound("Current password is incorrect");
		}

		user.setPassword(passwordEncoder.encode(req.getNewPassword()));
		userRepo.save(user);
		return "Password changed successfully";
	}

	// Request password reset via email OTP
	public String requestPasswordReset(String email) {
		User user = userRepo.findByEmail(email).orElseThrow(() -> new RuntimeException("Invalid email"));
		String otp = generateOtp();
		user.setOtp(otp);
		user.setOtpExpriresAt(LocalDateTime.now().plusMinutes(10));
		userRepo.save(user);
		emailService.sendOtp(user.getEmail(), otp);
		return "OTP sent to email";
	}

	// Reset password using OTP sent to email
	public String resetPasswordWithOtp(ResetPasswordRequest req) {
		User user = userRepo.findByEmail(req.getEmail()).orElseThrow(() -> new RuntimeException("Invalid email"));

		if (req.getOtp() == null || !req.getOtp().equals(user.getOtp())) {
			throw new RuntimeException("Invalid OTP");
		}

		if (user.getOtpExpriresAt() == null || user.getOtpExpriresAt().isBefore(LocalDateTime.now())) {
			throw new RuntimeException("OTP expired");
		}

		// set new password
		user.setPassword(passwordEncoder.encode(req.getNewPassword()));
		user.setOtp(null);
		user.setOtpExpriresAt(null);
		userRepo.save(user);
		return "Password reset successfully";
	}

	@PreAuthorize("hasAuthority('USER_DELETE') or hasRole('ADMIN')")
	public String deleteUser(long id) {
		userRepo.deleteById(id);
		return "delete succefully";
	}

	@PreAuthorize("hasAuthority('USER_READ') or hasRole('ADMIN')")
	public List<UserResponce> getAllUser() {
		List<User> users = userRepo.findAll();

		return users.stream()
				.map(user -> new UserResponce(
						user.getId(),
						user.getFname(),
						user.getEmail(),
						user.getMobileNum(),
						user.getRole(),
						user.getLocalDateTime()))
				.toList();
	}

}
