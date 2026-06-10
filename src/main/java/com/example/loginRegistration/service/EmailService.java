package com.example.loginRegistration.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

	@Autowired
	private JavaMailSender mailSender;

	public void sendOtp(String email, String otp) {
		SimpleMailMessage smg = new SimpleMailMessage();
		smg.setTo(email);
		smg.setSubject("your otp for verification");
		smg.setText("your otp is " + otp + " this opt do not share with any one");
		mailSender.send(smg);
	}

}
