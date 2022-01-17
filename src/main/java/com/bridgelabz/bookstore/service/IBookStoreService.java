package com.bridgelabz.bookstore.service;
import com.bridgelabz.bookstore.dto.UserDto;
import com.bridgelabz.bookstore.model.User;
public interface IBookStoreService {
	User getUserDataByEmailId(String emailId,String password);
	User postUserData(UserDto userDto);

}
