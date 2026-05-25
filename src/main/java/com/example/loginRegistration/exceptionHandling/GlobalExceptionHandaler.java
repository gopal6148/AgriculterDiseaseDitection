package com.example.loginRegistration.exceptionHandling;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandaler extends ResponseEntityExceptionHandler {
	
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

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<String> handleRuntimeException(RuntimeException re) {
		// Generic runtime exceptions (e.g., invalid OTP, user not found when not using custom exceptions)
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(re.getMessage());
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
																  HttpHeaders headers,
																  HttpStatusCode status,
																  WebRequest request) {
		Map<String, String> errors = new HashMap<>();
		for (FieldError error : ex.getBindingResult().getFieldErrors()) {
			errors.put(error.getField(), error.getDefaultMessage());
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).headers(headers).body(errors);
	}

}
