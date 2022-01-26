package com.bridgelabz.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.bookstore.converter.Converter;
import com.bridgelabz.bookstore.dto.ForgotPasswordDto;
import com.bridgelabz.bookstore.dto.LoginDto;
import com.bridgelabz.bookstore.dto.ResetPasswordDto;
import com.bridgelabz.bookstore.dto.TokenDto;
import com.bridgelabz.bookstore.dto.UserDto;
import com.bridgelabz.bookstore.dto.UserResponseDto;
import com.bridgelabz.bookstore.model.User;
import com.bridgelabz.bookstore.service.BookStoreService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@CrossOrigin(originPatterns = "*")
@RequestMapping("/bookstore")
public class BookStoreController {
	
	@Autowired
	private BookStoreService bookStoreService;
	
	@Autowired
	private Converter converter;

	@PostMapping("/login")
	public TokenDto checkLoginCredentials(@RequestBody LoginDto loginDto) {
		log.info("calling service layer method : checkEmailIdAndPasswordForLogin() with argument"+loginDto);
		TokenDto tokenDto = bookStoreService.checkEmailIdAndPasswordForLogin(loginDto);
		log.info("credential checking done for the user and sent the token in response.");
		return tokenDto;
		
	}

	@PostMapping("/registration")
	public UserResponseDto saveUserData(@RequestBody UserDto userDto) {
		log.info("calling service layer method for registering user with arguments : "+userDto);
		User savedUser = bookStoreService.registerNewUser(userDto);
		UserResponseDto responseDto = converter.convertUserToRespDto(savedUser);
		return responseDto;
	}

	@PostMapping("/forgot-password")
	public String forgotPassword(@RequestBody ForgotPasswordDto passwordDto) {
		log.info("calling service layer method for forgot password.");
		User userByEmailId = bookStoreService.getUserByEmailId(passwordDto.getEmailId());
		return bookStoreService.forgotPassword(userByEmailId);
	}
	@PostMapping("/resetpassword/{token}")
	public UserResponseDto resetPassword(@RequestBody ResetPasswordDto resetPasswordDto,
			@PathVariable("token") String token) {
		log.info("calling service layer method for resetting user password.");
		User user = bookStoreService.resetUserPassword(resetPasswordDto, token);
		UserResponseDto respDto = converter.convertUserToRespDto(user);
		return respDto;
	}
}
