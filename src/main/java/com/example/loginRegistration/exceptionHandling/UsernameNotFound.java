package com.example.loginRegistration.exceptionHandling;

public class UsernameNotFound extends RuntimeException{
	
	public UsernameNotFound(String massage){
		super(massage);
	}

}
