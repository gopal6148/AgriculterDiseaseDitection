package com.example.loginRegistration.exceptionHandling;

public class UserAllRedyRegister extends RuntimeException{
	
	private String msg;
	
	public UserAllRedyRegister() {
		
	}
	
	public UserAllRedyRegister(String message) {
		super(message);
		this.msg = message;
	}

}
