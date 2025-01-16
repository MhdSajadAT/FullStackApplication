package com.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.entity.User;

@Service
public interface UserService {

	User addUser(User user);
	
	List<User> getAllUsers();

	boolean authenticate(String email, String password);

	boolean verifyOtp(String email, String otp);

	void registerUser(User user);
}
