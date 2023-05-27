package com.luv2code.springbootlibrary.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(UserNotFoundException.class)
	public final ResponseEntity<ErrorDetails> handleUserNotFoundException(Exception ex, WebRequest rqst) {
		
		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),
				ex.getMessage(),
				rqst.getDescription(false)); 
		
		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.NOT_FOUND);
		
	}
	
	@ExceptionHandler(BookNotFoundException.class)
	public final ResponseEntity<ErrorDetails> handleBookNotFoundException(Exception ex, WebRequest rqst) {
		
		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),
				ex.getMessage(),
				rqst.getDescription(false)); 
		
		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.NOT_FOUND);
		
	}
	
	@ExceptionHandler(PasswordMismatchException.class)
	public final ResponseEntity<ErrorDetails> handlePasswordMismatchException(Exception ex, WebRequest rqst) {
		
		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),
				ex.getMessage(),
				rqst.getDescription(false)); 
		
		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.EXPECTATION_FAILED);
		
	}
	
	@ExceptionHandler(UserInactiveException.class)
	public final ResponseEntity<ErrorDetails> handleUserInactiveException(Exception ex, WebRequest rqst) {
		
		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),
				ex.getMessage(),
				rqst.getDescription(false)); 
		
		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.EXPECTATION_FAILED);
		
	}
	
	@ExceptionHandler(AuthenticationException.class)
	public final ResponseEntity<ErrorDetails> handleAuthenticationException(Exception ex, WebRequest rqst) {
		
		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),
				ex.getMessage(),
				rqst.getDescription(false)); 
		
		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.EXPECTATION_FAILED);
		
	}
	
	@ExceptionHandler(DuplicatedUserInfoException.class)
	public final ResponseEntity<ErrorDetails> handleDuplicatedUserInfoException(Exception ex, WebRequest rqst) {
		
		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),
				ex.getMessage(),
				rqst.getDescription(false)); 
		
		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.CONFLICT);
		
	}

	@ExceptionHandler(BookAlreadyCheckedOutByUserException.class)
	public final ResponseEntity<ErrorDetails> handleBookAlreadyCheckedOutByUserException(Exception ex, WebRequest rqst) {
		
		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),
				ex.getMessage(),
				rqst.getDescription(false)); 
		
		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.CONFLICT);
		
	}
	
	@ExceptionHandler(ReviewAlreadyCreatedException.class)
	public final ResponseEntity<ErrorDetails> handleReviewAlreadyCreatedException(Exception ex, WebRequest rqst) {
		
		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),
				ex.getMessage(),
				rqst.getDescription(false)); 
		
		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.CONFLICT);
		
	}
}
