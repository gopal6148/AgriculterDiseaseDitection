package com.example.User_Service.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserResponse {
	private String username;
	private String email;
	private Long mobileNum;
	
	
	public UserResponse(String username, String email, Long mobileNum) {
		super();
		this.username = username;
		this.email = email;
		this.mobileNum = mobileNum;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Long getMobileNum() {
		return mobileNum;
	}
	public void setMobileNum(Long mobileNum) {
		this.mobileNum = mobileNum;
	}
	
	

}
