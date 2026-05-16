package com.example.loginRegistration.exceptionHandling;

public class PasswordNotFound extends RuntimeException{
	
	private String msg;
	
	
	public PasswordNotFound() {
		super();
	}


	public PasswordNotFound(String message) {
		super(message);
		this.msg = message;
	}

}
