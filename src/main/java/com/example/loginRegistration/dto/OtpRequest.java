package com.example.loginRegistration.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class OtpRequest {

	@NotBlank(message = "email cannot be empty")
	@Email(message = "Invalid email format")
	private String email;

	@NotBlank(message = "OTP is required")
	private String otp;

	public OtpRequest() {
		// default constructor
	}

	public OtpRequest(String email, String otp) {
		super();
		this.email = email;
		this.otp = otp;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

}
