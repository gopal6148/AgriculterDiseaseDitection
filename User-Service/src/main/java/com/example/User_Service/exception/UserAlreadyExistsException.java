package com.example.User_Service.exception;

public class UserAlreadyExistsException extends RuntimeException{
	
	public UserAlreadyExistsException(String massage) {
		super(massage);
	}

}
