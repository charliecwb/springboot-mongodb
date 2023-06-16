package com.charliecwb.springbootmongodb.services.exception;

public class InvalidTwoFACodeException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InvalidTwoFACodeException(String code) {
			super("Invalid informed code "+ code);
		}

}
