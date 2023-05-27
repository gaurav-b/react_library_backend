package com.luv2code.springbootlibrary.service;

import java.util.Optional;

import com.luv2code.springbootlibrary.entity.AppUser;

public interface AppUserService {

	Optional<AppUser> getUserByUsername(String username);
	boolean hasUserWithUsername(String username);
}
