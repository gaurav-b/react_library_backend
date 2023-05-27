package com.luv2code.springbootlibrary.exception;

@SuppressWarnings("serial")
public class BookAlreadyCheckedOutByUserException extends RuntimeException {

    public BookAlreadyCheckedOutByUserException(String message) {
        super(message);
    }
}
