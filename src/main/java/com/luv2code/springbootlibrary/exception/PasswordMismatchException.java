package com.luv2code.springbootlibrary.exception;

@SuppressWarnings("serial")
public class PasswordMismatchException extends RuntimeException {

    public PasswordMismatchException(String message) {
        super(message);
    }
}
