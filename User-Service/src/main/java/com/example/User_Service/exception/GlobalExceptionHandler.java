package com.example.User_Service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<String> handleUserNotFound(UserNotFoundException uex) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(uex.getMessage());
	}
	
	
	@ExceptionHandler(UserAlreadyExistsException.class)
	public ResponseEntity<String> handleUserExists(UserAlreadyExistsException uaex) {
		return ResponseEntity.status(HttpStatus.CONFLICT).body(uaex.getMessage());
	}

}
