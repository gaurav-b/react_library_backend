package com.luv2code.springbootlibrary.util;

public interface AppConstants {
	
	String ADMIN = "Admin";
    String SUPER_ADMIN = "SuperAdmin";
    String USER = "User";
 
    String REQUEST_TYPE_OPEN = "Open";
    String REQUEST_TYPE_CLOSE = "Close";
    
    String TOKEN_TYPE = "JWT";
    String TOKEN_ISSUER = "library-api";
    String TOKEN_AUDIENCE = "react-library";
    
    String TOKEN_HEADER = "Authorization";
    String TOKEN_PREFIX = "Bearer ";
    
    String VALIDATION_FAILED_DETAIL = "Form fields are invalid, please correct and resubmit!";
    
    public static final String BEARER_KEY_SECURITY_SCHEME = "bearer-key";
}
