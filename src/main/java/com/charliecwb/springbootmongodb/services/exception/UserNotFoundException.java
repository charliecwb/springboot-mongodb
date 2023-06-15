package com.charliecwb.springbootmongodb.services.exception;

public class UserNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UserNotFoundException(String text) {
			super("User not found. UserName "+ text);
		}

}
