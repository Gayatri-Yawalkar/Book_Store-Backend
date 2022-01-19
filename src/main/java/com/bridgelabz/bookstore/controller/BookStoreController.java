package com.bridgelabz.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.bookstore.converter.Converter;
import com.bridgelabz.bookstore.dto.ForgotPasswordDto;
import com.bridgelabz.bookstore.dto.LoginDto;
import com.bridgelabz.bookstore.dto.ResetPasswordDto;
import com.bridgelabz.bookstore.dto.UserDto;
import com.bridgelabz.bookstore.dto.UserResponseDto;
import com.bridgelabz.bookstore.model.User;
import com.bridgelabz.bookstore.service.BookStoreService;
import com.bridgelabz.bookstore.service.MailService;

@RestController
@CrossOrigin(allowedHeaders = "*", origins = "*")
@RequestMapping("/bookstore")
public class BookStoreController {
	
	@Autowired
	private BookStoreService bookStoreService;
	
	@Autowired
	private MailService mailService;
	
	@Autowired
	private Converter converter;

	@PostMapping("/login")
	public UserResponseDto checkLoginCredentials(LoginDto loginDto) {
		User user = bookStoreService.checkEmailIdAndPassword(loginDto);
		UserResponseDto respDto = converter.convertUserToRespDto(user);
		return respDto;
	}

	@PostMapping("/registration")
	public UserResponseDto saveUserData(@RequestBody UserDto userDto) {
		User savedUser = bookStoreService.postUserData(userDto);
		UserResponseDto responseDto = converter.convertUserToRespDto(savedUser);
		return responseDto;
	}

	@PostMapping("/forgot-password")
	public String forgotPassword(@RequestBody ForgotPasswordDto passwordDto) {
		User userByEmailId = bookStoreService.getUserByEmailId(passwordDto.getEmailId());
		if(userByEmailId != null) {
			mailService.sendNotification(userByEmailId.getEmailId(), userByEmailId.getUserId());
			return "Password Reset Link has been sent to your Email.";
		} else {
			return "Password Reset Link has been sent to your Email.";
		}
	}
	@PutMapping("/resetpassword/{token}")
	public UserResponseDto resetPassword(@RequestBody ResetPasswordDto resetPasswordDto,
			@PathVariable("token") String token) {
		User user = bookStoreService.resetUserPassword(resetPasswordDto, token);
		UserResponseDto respDto = converter.convertUserToRespDto(user);
		return respDto;
	}
}
