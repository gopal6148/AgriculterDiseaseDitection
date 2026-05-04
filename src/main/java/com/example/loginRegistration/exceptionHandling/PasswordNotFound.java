package com.example.loginRegistration.exceptionHandling;

public class PasswordNotFound extends RuntimeException{
	
	public PasswordNotFound(String massage) {
		super(massage);
	}

}
