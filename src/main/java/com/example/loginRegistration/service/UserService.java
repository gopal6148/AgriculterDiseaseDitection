package com.example.loginRegistration.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;  
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.loginRegistration.dto.Registration;
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
		user.setLocalDateTime(LocalDateTime.now());
		userRepo.save(user);
		
		return "user register successfully";
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
	                .orElseThrow(() -> new RuntimeException("User not found"));

						// Ensure the granted authority uses the ROLE_ prefix so hasRole(...) checks work
						return new org.springframework.security.core.userdetails.User(
								user.getEmail(),
								user.getPassword(),
								List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole().name()))
						);
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
