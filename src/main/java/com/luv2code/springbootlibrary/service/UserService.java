package com.luv2code.springbootlibrary.service;

import java.util.List;

import com.luv2code.springbootlibrary.entity.User;

public interface UserService {
	public User saveUser(User User);
	public User fetchOne(String userId);
	public void deleteOne(String userId);
	public List<User> fetchAll();
	public String updateUser(User existingUser);
}
