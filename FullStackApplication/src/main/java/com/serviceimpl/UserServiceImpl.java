package com.serviceimpl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.entity.User;
import com.repo.UserRepository;
import com.service.OtpService;
import com.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
@Override
public User addUser(User user) {
	// TODO Auto-generated method stub
	return null;
}

@Override
public List<User> getAllUsers() {
	// TODO Auto-generated method stub
	return null;
}

@Override
public boolean authenticate(String email, String password) {
	
	Optional<User> user=userRepository.findByEmail(email);
	if(user.isPresent()) {
		if(user.get().getEmail().equals(email)&&user.get().getPassword().equals(password)) {
			return true;
		}
	}
	return false;
}

@Override
public boolean verifyOtp(String email, String otp) {
	// TODO Auto-generated method stub
	return false;
}

@Override
public void registerUser(User user) {
	
	user.setCreatedAt(LocalDate.now());
	userRepository.save(user);
	
}

}
