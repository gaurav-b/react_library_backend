package com.luv2code.springbootlibrary.exception;

@SuppressWarnings("serial")
public class UserInactiveException extends RuntimeException {
	public UserInactiveException(String message) {
		super(message);
	}
}
