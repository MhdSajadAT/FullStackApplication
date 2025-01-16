package com.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.service.OtpService;

@Service
public class OtpServiceImpl implements OtpService {

//	@Autowired
//	private JavaMailSender mailSender;

	@Override
	public boolean sendOtpToEmail(String email, String otp) {

		try {

//			SimpleMailMessage message = new SimpleMailMessage();
//
//			message.setTo(email);
//			message.setSubject("Your OTP for Verification");
//			message.setText("Your OTP is: " + otp + "\nThis OTP is valid for 5 minutes.");
//
//			mailSender.send(message);

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
