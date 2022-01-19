package com.bridgelabz.bookstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.bookstore.converter.DtoToEntityConverter;
import com.bridgelabz.bookstore.dto.UserDto;
import com.bridgelabz.bookstore.model.User;
import com.bridgelabz.bookstore.repository.BookStoreRepository;


@Service
public class BookStoreService implements IBookStoreService {
	
	@Autowired
	private BookStoreRepository bookStoreRepository;
	
	@Autowired
	private DtoToEntityConverter dtoToEntityConverter;

	@Override
	public User getUserDataByEmailId(String emailId, String password) {
		User user = bookStoreRepository.findByEmailId(emailId);
		if (user.getPassword().equals(password)) {
			return user;
		} else {
			return null;
		}
	}

	@Override
	public User postUserData(UserDto userDto) {
		User user = dtoToEntityConverter.convertDtoToEntity(userDto);
		return bookStoreRepository.save(user);
	}
	
	public User getUserByEmailId(String emailId) {
		User user = bookStoreRepository.findByEmailId(emailId);
		if(user != null) {
			return user;
		}
		return null;
	}
	
	

}
