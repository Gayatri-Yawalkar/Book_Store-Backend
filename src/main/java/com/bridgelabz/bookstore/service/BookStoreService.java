package com.bridgelabz.bookstore.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.bridgelabz.bookstore.converter.Converter;
import com.bridgelabz.bookstore.dto.LoginDto;
import com.bridgelabz.bookstore.dto.ResetPasswordDto;
import com.bridgelabz.bookstore.dto.UserDto;
import com.bridgelabz.bookstore.exception.BookStoreException;
import com.bridgelabz.bookstore.model.User;
import com.bridgelabz.bookstore.repository.UserRepository;
import com.bridgelabz.bookstore.utilis.JwtUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BookStoreService implements IBookStoreService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private Converter converter;

	@Autowired
	private JwtUtil jwt;

	@Autowired
	MailService mailService;

	@Autowired
	private BCryptPasswordEncoder encoder;

	@Override
	public User checkEmailIdAndPasswordForLogin(LoginDto loginDto) {
		String emailId = loginDto.getEmail();
		String password = loginDto.getPassword();
		log.info("finding user data in DB of this user : "+loginDto);
		User user = userRepository.findByEmailId(emailId);
		if (user != null) {
			log.info("User found in DB with above details, matching password");
			if (encoder.matches(password, user.getPassword())) {
				log.info("password matched");
				return user;
			} else {
				log.error("Password Not Matched");
				throw new BookStoreException("Wrong Password");
			}
		} else {
			log.error("User not found with the above details");
			throw new BookStoreException("UnAuthorized User");
		}
	}

	@Override
	public User registerNewUser(UserDto userDto) {
		User userByEmailId = userRepository.findByEmailId(userDto.getEmailId());
		log.info("Checking user already exists in our system or not with user details : "+userDto);
		if (userByEmailId != null) {
			log.error("User already exists in our system, cannot register with same email");
			throw new BookStoreException("Email Id already registered, Use Different Email Id.");
		} else {
			log.info("User didn't exist in our system.");
			LocalDateTime createdAtTime = LocalDateTime.now();
			User user = converter.convertDtoToEntity(userDto);
			user.setCreatedAt(createdAtTime);
			user.setUpdatedAt(createdAtTime);
			String encodedPwd = encoder.encode(user.getPassword());
			user.setPassword(encodedPwd);
			log.info("User registered succesfully");
			return userRepository.save(user);
		}
	}

	public String forgotPassword(User userByEmailId) {
		if (userByEmailId != null) {
			log.info("user found in our system registered with his email id.");
			mailService.sendNotification(userByEmailId.getEmailId(), userByEmailId.getUserId());
			log.info("Password reset link sent to the email id.");
			return "Password Reset Link has been sent to your Email.";
		} else {
			log.error("user not found in our system with his email id.");
			return "Password Reset Link has been sent to your Email.";
		}
	}

	@Override
	public User resetUserPassword(ResetPasswordDto password, String token) throws BookStoreException {
		log.info("Getting email from the token"+token);
		String email = jwt.getEmailFromToken(token);
		log.info("Checking user is present in our DB with fetched email : "+email);
		User user = userRepository.findByEmailId(email);
		if (user != null) {
			log.info("User found in our system with above email.");
			LocalDateTime updatedAtTime = LocalDateTime.now();
			user.setPassword(encoder.encode(password.getConfirmPassword()));
			user.setUpdatedAt(updatedAtTime);
			log.info("Password updated Successfully");
			return userRepository.save(user);
		} else {
			log.error("User not found in our system with email : "+email);
			throw new BookStoreException(
					"Something went wrong while changing your password, Please try again after some time.");
		}
	}

	public User getUserByEmailId(String emailId) {
		User user = userRepository.findByEmailId(emailId);
		if (user != null) {
			return user;
		}
		return null;
	}

}
