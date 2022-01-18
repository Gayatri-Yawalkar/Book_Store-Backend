package com.bridgelabz.bookstore.service;
import com.bridgelabz.bookstore.dto.LoginDto;
import com.bridgelabz.bookstore.dto.UserDto;
import com.bridgelabz.bookstore.model.User;
public interface IBookStoreService {
	User checkEmailIdAndPassword(LoginDto loginDto);
	User postUserData(UserDto userDto);
}
