package com.controller;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.service.LoginRequest;
import com.service.OtpRequest;
import com.service.OtpService;
import com.service.UserService;

@RestController
@RequestMapping("/api")
public class AuthController {

    
    private static String generatedOtp = "";
    private static String emailForOtp = "";
    private static final long OTP_EXPIRATION_TIME = 5 * 60 * 1000; 
    private static long otpTimestamp = 0;

    @Autowired
	private UserService userService;
    @Autowired
    private OtpService otpService;  

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        if (userService.authenticate(email, password)) {
            String otp = generateOtp(); 
            otpTimestamp = System.currentTimeMillis(); 
            emailForOtp = email;  

            boolean otpSent = otpService.sendOtpToEmail(email, otp); 

            if (otpSent) {
                return ResponseEntity.ok("Login successful! OTP has been sent to your email.");
            } else {
                return ResponseEntity.status(500).body("Error sending OTP. Please try again.");
            }
        }

        return ResponseEntity.status(401).body("Invalid credentials.");
    }

    
    @PostMapping("/verifyOtp")
    public ResponseEntity<String> verifyOtp(@RequestBody OtpRequest otpRequest) {
        String otp = otpRequest.getOtp();
        String email = otpRequest.getEmail();
        if (emailForOtp.equals(email) && generatedOtp.equals(otp) && (System.currentTimeMillis() - otpTimestamp <= OTP_EXPIRATION_TIME)) {
            return ResponseEntity.ok("OTP verified successfully! You are now logged in.");
        }
        return ResponseEntity.status(400).body("Invalid OTP or OTP expired.");
    }

   
    private String generateOtp() {
        Random random = new Random();
        StringBuilder otp = new StringBuilder(6);
        for (int i = 0; i < 6; i++) {
            otp.append(random.nextInt(10));  
        }
        generatedOtp = otp.toString();  
        return generatedOtp;
    }
}
