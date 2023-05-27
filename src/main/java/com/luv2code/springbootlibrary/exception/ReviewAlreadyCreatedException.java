package com.luv2code.springbootlibrary.exception;

@SuppressWarnings("serial")
public class ReviewAlreadyCreatedException extends Exception {

	public ReviewAlreadyCreatedException(String message) {
        super(message);
    }
}
