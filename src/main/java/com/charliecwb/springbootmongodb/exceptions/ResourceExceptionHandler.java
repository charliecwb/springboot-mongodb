package com.charliecwb.springbootmongodb.exceptions;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.charliecwb.springbootmongodb.services.exception.InvalidLoginPasswordException;
import com.charliecwb.springbootmongodb.services.exception.InvalidTwoFACodeException;
import com.charliecwb.springbootmongodb.services.exception.ObjectNotFoundException;
import com.charliecwb.springbootmongodb.services.exception.UserNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest req) {
		StandardError err = new StandardError(Instant.now(), HttpStatus.NOT_FOUND.value(), "Object not found",
				e.getMessage(), req.getRequestURI());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<StandardError> userNotFound(UserNotFoundException e, HttpServletRequest req) {
		StandardError err = new StandardError(Instant.now(), HttpStatus.NOT_FOUND.value(), "User not found",
				e.getMessage(), req.getRequestURI());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}
	
	@ExceptionHandler(InvalidLoginPasswordException.class)
	public ResponseEntity<StandardError> invalidLoginPassword(InvalidLoginPasswordException e, HttpServletRequest req) {
		StandardError err = new StandardError(Instant.now(), HttpStatus.UNAUTHORIZED.value(), "User not authorized",
				e.getMessage(), req.getRequestURI());
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(err);
	}	
	
	@ExceptionHandler(InvalidTwoFACodeException.class)
	public ResponseEntity<StandardError> invalidTwoFACode(InvalidTwoFACodeException e, HttpServletRequest req) {
		StandardError err = new StandardError(Instant.now(), HttpStatus.NOT_ACCEPTABLE.value(), "Invalid two factor code",
				e.getMessage(), req.getRequestURI());
		return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(err);
	}	
}
