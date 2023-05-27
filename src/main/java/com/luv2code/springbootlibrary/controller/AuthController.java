package com.luv2code.springbootlibrary.controller;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.luv2code.springbootlibrary.dto.AuthResponse;
import com.luv2code.springbootlibrary.dto.LoginDTO;
import com.luv2code.springbootlibrary.dto.SignUpDTO;
import com.luv2code.springbootlibrary.entity.AppUser;
import com.luv2code.springbootlibrary.entity.User;
import com.luv2code.springbootlibrary.exception.DuplicatedUserInfoException;
import com.luv2code.springbootlibrary.exception.ErrorDetails;
import com.luv2code.springbootlibrary.exception.UserInactiveException;
import com.luv2code.springbootlibrary.security.TokenProvider;
import com.luv2code.springbootlibrary.service.AppUserService;
import com.luv2code.springbootlibrary.service.UserService;
import com.luv2code.springbootlibrary.util.AppConstants;
import com.luv2code.springbootlibrary.util.AppUtils;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	private final UserService userService;
	private final AppUserService appUserService;
	private final PasswordEncoder passwordEncoder;
	private final AuthenticationManager authenticationManager;
	private final TokenProvider tokenProvider;

	public AuthController(UserService userService, AppUserService appUserService, 
			PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, TokenProvider tokenProvider) {
		super();
		this.userService = userService;
		this.appUserService = appUserService;
		this.passwordEncoder = passwordEncoder;
		this.authenticationManager = authenticationManager;
		this.tokenProvider = tokenProvider;
	}

	@PostMapping("/authenticate")
	public AuthResponse login(@Valid @RequestBody LoginDTO loginDto) {
		String token = authenticateAndGetToken(loginDto.getUsername(), loginDto.getPassword());
		return new AuthResponse(token);
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/signup")
	public AuthResponse signUp(@Valid @RequestBody SignUpDTO signUpDto) {
		if (appUserService.hasUserWithUsername(signUpDto.getUsername())) {
			throw new DuplicatedUserInfoException(String.format("Username %s already been used", signUpDto.getUsername()));
		}

		User savedUser=null;
		String token ="";

		// map details to Customer
		User userToBeSaved = new User();
		userToBeSaved.setfName(signUpDto.getfName());
		userToBeSaved.setmName(signUpDto.getmName());
		userToBeSaved.setlName(signUpDto.getlName());
		userToBeSaved.setEmail(signUpDto.getEmail());
		userToBeSaved.setContactNumber(signUpDto.getContactNumber());
		userToBeSaved.setAddress(signUpDto.getAddress());

		AppUser appUserToBeSaved = new AppUser();
		appUserToBeSaved.setUsername(signUpDto.getUsername());
		appUserToBeSaved.setRole(AppConstants.USER);
		appUserToBeSaved.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
		appUserToBeSaved.setIsActive(true);

		userToBeSaved.setAppUser(appUserToBeSaved);
		appUserToBeSaved.setUser(userToBeSaved);

		// call service to save customer
		savedUser = userService.saveUser(userToBeSaved);
		token = authenticateAndGetToken(savedUser.getAppUser().getUsername(), signUpDto.getPassword());

		return new AuthResponse(token);
	}

	private String authenticateAndGetToken(String username, String password) throws UserInactiveException {
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		return tokenProvider.generate(authentication);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public final ResponseEntity<ErrorDetails> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){

		StringBuilder sbError = new StringBuilder();

		ex.getBindingResult().getAllErrors().forEach(AppUtils.fetchErrors(sbError));

		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),
				AppConstants.VALIDATION_FAILED_DETAIL, sbError.toString()); 

		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.EXPECTATION_FAILED);
	}
}
