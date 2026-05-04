package com.example.loginRegistration.exceptionHandling;

public class MobileNumberAllreadyExist extends RuntimeException{
	public MobileNumberAllreadyExist(String massage) {
		super(massage);
	}

}
