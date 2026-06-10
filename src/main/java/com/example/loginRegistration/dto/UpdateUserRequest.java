package com.example.loginRegistration.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UpdateUserRequest {

	@NotBlank(message = "Username cannot be empty")
	@Size(min = 3, max = 40, message = "Username must be between 3-40 characters")
	private String fname;

	@NotBlank(message = "mobile number cannot be empty")
	@Size(max = 10)
	private String mobileNum;

	public UpdateUserRequest() {
	}

	public UpdateUserRequest(String fname, String mobileNum) {
		this.fname = fname;
		this.mobileNum = mobileNum;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getMobileNum() {
		return mobileNum;
	}

	public void setMobileNum(String mobileNum) {
		this.mobileNum = mobileNum;
	}

}
