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

@Service
public class BookStoreService implements IBookStoreService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private Converter converter;

	@Autowired
	private JwtUtil jwt;
	
	@Autowired
	private BCryptPasswordEncoder encoder;

	@Override
	public User checkEmailIdAndPassword(LoginDto loginDto) {
		String emailId = loginDto.getEmail();
		String password = loginDto.getPassword();
		User user = userRepository.findByEmailId(emailId);
		if (user != null) {
			if (encoder.matches(password, user.getPassword())) {
				return user;
			} else {
				throw new BookStoreException("Wrong Password");
			}
		} else {
			throw new BookStoreException("UnAuthorized User");
		}
	}

	@Override
	public User postUserData(UserDto userDto) {
		User userByEmailId = userRepository.findByEmailId(userDto.getEmailId());
		
		if (userByEmailId != null) {
			throw new BookStoreException("Email Id already registered, Use Different Email Id.");
		} else {
			LocalDateTime createdAtTime=LocalDateTime.now();
			User user = converter.convertDtoToEntity(userDto);
			user.setCreatedAt(createdAtTime);
			user.setUpdatedAt(createdAtTime);
			String encodedPwd = encoder.encode(user.getPassword());
			user.setPassword(encodedPwd);
			return userRepository.save(user);
		}
	}

	@Override
	public User resetUserPassword(ResetPasswordDto password, String token) throws BookStoreException {
		String email = jwt.getSubject(token);
		User user = userRepository.findByEmailId(email);
		if (user != null) {
			LocalDateTime updatedAtTime=LocalDateTime.now();
			user.setPassword(password.getConfirmPassword());
			user.setUpdatedAt(updatedAtTime);
			return userRepository.save(user);
		} else {
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
