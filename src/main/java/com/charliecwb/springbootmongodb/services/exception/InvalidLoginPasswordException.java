package com.charliecwb.springbootmongodb.services.exception;

public class InvalidLoginPasswordException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InvalidLoginPasswordException(String text) {
			super("Invalid password "+ text);
		}

}
