package com.luv2code.springbootlibrary.exception;

@SuppressWarnings("serial")
public class BookNotFoundException extends RuntimeException {

    public BookNotFoundException(String message) {
        super(message);
    }
}
