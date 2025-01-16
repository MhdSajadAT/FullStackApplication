package com.service;

import org.springframework.stereotype.Service;

@Service
public interface OtpService {
	boolean sendOtpToEmail(String email, String otp);
}
