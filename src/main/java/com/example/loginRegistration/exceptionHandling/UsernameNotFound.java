package com.example.loginRegistration.exceptionHandling;

public class UsernameNotFound extends RuntimeException{
	
	private String msg;
	
	public UsernameNotFound() {};
	
	public UsernameNotFound(String message){
		super(message);
		this.msg = message;
	}

}
