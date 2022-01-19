package com.bridgelabz.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

<<<<<<< HEAD
import com.bridgelabz.bookstore.converter.DtoToEntityConverter;
import com.bridgelabz.bookstore.dto.ForgotPasswordDto;
=======
import com.bridgelabz.bookstore.converter.Converter;
import com.bridgelabz.bookstore.dto.LoginDto;
import com.bridgelabz.bookstore.dto.ResetPasswordDto;
>>>>>>> e0d7e3695546725e7afda7b4a31f4d4e012df86c
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
<<<<<<< HEAD
	private DtoToEntityConverter dtoToEntityConverter;
	
	@Autowired
	private MailService mailService;
	
	@GetMapping(value = "/getuser/{emailId}/{password}", produces = "application/json")
	public UserResponseDto getUserData(@PathVariable("emailId") String emailId,
			@PathVariable("password") String password) {
		User user = bookStoreService.getUserDataByEmailId(emailId, password);
		UserResponseDto respDto = dtoToEntityConverter.convertUserToRespDto(user);
=======
	private Converter converter;

	@PostMapping("/login")
	public UserResponseDto checkLoginCredentials(LoginDto loginDto) {
		User user = bookStoreService.checkEmailIdAndPassword(loginDto);
		UserResponseDto respDto = converter.convertUserToRespDto(user);
>>>>>>> e0d7e3695546725e7afda7b4a31f4d4e012df86c
		return respDto;
	}

	@PostMapping("/registration")
	public UserResponseDto saveUserData(@RequestBody UserDto userDto) {
		User savedUser = bookStoreService.postUserData(userDto);
		UserResponseDto responseDto = converter.convertUserToRespDto(savedUser);
		return responseDto;
	}

	@PutMapping("/resetpassword/{token}")
	public UserResponseDto resetPassword(@RequestBody ResetPasswordDto resetPasswordDto,
			@PathVariable("token") String token) {
		User user = bookStoreService.resetUserPassword(resetPasswordDto, token);
		UserResponseDto respDto = converter.convertUserToRespDto(user);
		return respDto;
	}
}
