package com.example.User_Service.exception;

public class InvalidCredentialsException extends RuntimeException{
	
	public InvalidCredentialsException(String massage) {
		super(massage);
	}

}
