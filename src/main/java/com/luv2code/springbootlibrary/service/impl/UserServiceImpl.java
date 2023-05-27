package com.luv2code.springbootlibrary.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.luv2code.springbootlibrary.dao.UserRepository;
import com.luv2code.springbootlibrary.entity.User;
import com.luv2code.springbootlibrary.exception.UserNotFoundException;
import com.luv2code.springbootlibrary.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	private UserRepository userRepository;
	

	public UserServiceImpl(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	@Override
	public User saveUser(User user) {
		user.setIsFirstLogin(true);
		user.setIsVerified(false);
		user.setCrtdDate(LocalDate.now());
		user.setLstUpdtDate(LocalDate.now());
		user.setLstUpdtBy("system");
		
		user = userRepository.save(user);
		return user;
	}
	
	
	@Override
	public User fetchOne(String userId) {
		Optional<User> user = userRepository.findById(userId);
		if(user.isEmpty()) {
			throw new UserNotFoundException("No User record found with the provided ID: "+userId);
		}
		return user.get();
	}


	@Override
	public void deleteOne(String userId) {
		User user = fetchOne(userId);
		userRepository.deleteById(user.getUserId());
	}


	@Override
	public List<User> fetchAll() {
		return userRepository.findAll();
	}


	@Override
	public String updateUser(User existingUser) {
		existingUser = userRepository.save(existingUser);
		return existingUser.getUserId();
	}

	
}
