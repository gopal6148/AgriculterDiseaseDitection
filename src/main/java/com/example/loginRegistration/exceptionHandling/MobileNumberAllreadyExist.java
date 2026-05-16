package com.example.loginRegistration.exceptionHandling;

public class MobileNumberAllreadyExist extends RuntimeException{
	
	private String msg;
	
	
	public MobileNumberAllreadyExist() {
	}


	public MobileNumberAllreadyExist(String message) {
		super(message);
		this.msg = message;
	}

}
