package com.example.loginRegistration.exceptionHandling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandaler {
	
	@ExceptionHandler(UsernameNotFound.class)
	public ResponseEntity<String> handleUserNotFoundException(UsernameNotFound unfe) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(unfe.getMessage());
	}
	
	@ExceptionHandler(UserAllRedyRegister.class)
	public ResponseEntity<String> handleUserAllRedyRegister(UserAllRedyRegister uarr) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(uarr.getMessage());
	}
	
	@ExceptionHandler(PasswordNotFound.class)
	public ResponseEntity<String> handlePasswordNotFound(PasswordNotFound pnf) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(pnf.getMessage());
	}
	
	@ExceptionHandler(MobileNumberAllreadyExist.class)
	public ResponseEntity<String> handleMobileNumberAllradyexist(MobileNumberAllreadyExist mnare) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mnare.getMessage());
	}

}
