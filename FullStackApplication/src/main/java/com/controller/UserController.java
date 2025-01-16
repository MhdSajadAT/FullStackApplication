package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.entity.User;
import com.service.LoginRequest;
import com.service.OtpRequest;
import com.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/createUser")
	public User createUser(@RequestBody User user) {
		return userService.addUser(user);
	}

	@PostMapping("/register")
	public String registerUser(@RequestBody User user) {
		userService.registerUser(user);
		return "User registered!";
	}

	 @PostMapping("/verifyOtp")
	    public String verifyOtp(@RequestBody OtpRequest otpRequest) {
	    
	        if (userService.verifyOtp(otpRequest.getEmail(),otpRequest.getOtp())) {
	            // OTP is valid, allow login and generate a token if needed
	            return "OTP verified successfully! Welcome to your dashboard.";
	        }
	        return "Invalid OTP. Please try again.";
	    }

	@PostMapping("/login")
	public String login(@RequestBody LoginRequest loginRequest) {
	    String email = loginRequest.getEmail();
	    String password = loginRequest.getPassword();

	    if (userService.authenticate(email, password)) {
	        return "Login successful! Welcome to your dashboard.";
	    }
	    return "Invalid credentials or email not verified.";
	}

}
