package com.example.loginRegistration.dto;

import java.time.LocalDateTime;

import com.example.loginRegistration.enm.Role;

public class UserResponce {
	
	private Long id;
	
	private String fname;
	
	private String email;
	
	private Role role;
	
	 private String mobileNum;
	 
	 private LocalDateTime localDateTime;
	 
	 public UserResponce() {
	    }

	    public UserResponce(Long id, String fname, String email,
	            String mobileNum, Role role,
	            LocalDateTime localDateTime) {

	        this.id = id;
	        this.fname = fname;
	        this.email = email;
	        this.mobileNum = mobileNum;
	        this.role = role;
	        this.localDateTime = localDateTime;
	    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getMobileNum() {
		return mobileNum;
	}

	public void setMobileNum(String mobileNum) {
		this.mobileNum = mobileNum;
	}

	public LocalDateTime getLocalDateTime() {
		return localDateTime;
	}

	public void setLocalDateTime(LocalDateTime localDateTime) {
		this.localDateTime = localDateTime;
	}
	
	
	

}
