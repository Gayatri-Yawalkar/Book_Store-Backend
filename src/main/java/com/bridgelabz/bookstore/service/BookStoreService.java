package com.bridgelabz.bookstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.bookstore.converter.Converter;
import com.bridgelabz.bookstore.dto.LoginDto;
import com.bridgelabz.bookstore.dto.ResetPasswordDto;
import com.bridgelabz.bookstore.dto.UserDto;
import com.bridgelabz.bookstore.exception.BookStoreException;
import com.bridgelabz.bookstore.model.User;
import com.bridgelabz.bookstore.repository.UserRepository;
import com.bridgelabz.bookstore.utilis.TokenUtil;

@Service
public class BookStoreService implements IBookStoreService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private Converter converter;

	@Override
	public User checkEmailIdAndPassword(LoginDto loginDto) {
		String emailId = loginDto.getEmail();
		String password = loginDto.getPassword();
		User user = userRepository.findByEmailId(emailId);
		if (user != null) {
			if (user.getPassword().equals(password)) {
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
		User user = converter.convertDtoToEntity(userDto);
		return userRepository.save(user);
	}

	@Override
	public User resetUserPassword(ResetPasswordDto password, String token) throws BookStoreException {
		Integer id = TokenUtil.decodeToken(token);
		User user = userRepository.findById(id).orElseThrow(() -> new BookStoreException("User not found "));
		user.setPassword(password.getConfirmPassword());
		return userRepository.save(user);
	}

	public User getUserByEmailId(String emailId) {
		User user = userRepository.findByEmailId(emailId);
		if (user != null) {
			return user;
		}
		return null;
	}

}
