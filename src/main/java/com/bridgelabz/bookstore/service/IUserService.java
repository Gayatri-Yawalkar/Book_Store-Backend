package com.bridgelabz.bookstore.service;

import com.bridgelabz.bookstore.dto.LoginDto;
import com.bridgelabz.bookstore.dto.ResetPasswordDto;
import com.bridgelabz.bookstore.dto.TokenDto;
import com.bridgelabz.bookstore.dto.UserDto;
import com.bridgelabz.bookstore.exception.BookStoreException;
import com.bridgelabz.bookstore.model.User;

public interface IUserService {

	TokenDto checkEmailIdAndPasswordForLogin(LoginDto loginDto);

	User registerNewUser(UserDto userDto);

	public User resetUserPassword(ResetPasswordDto password, String token) throws BookStoreException;

}
