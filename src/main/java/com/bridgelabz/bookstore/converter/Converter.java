package com.bridgelabz.bookstore.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.bridgelabz.bookstore.dto.BooksDto;
import com.bridgelabz.bookstore.dto.UserDto;
import com.bridgelabz.bookstore.dto.UserResponseDto;
import com.bridgelabz.bookstore.model.Books;
import com.bridgelabz.bookstore.model.User;

@Component
public class Converter {

	public User convertDtoToEntity(UserDto userDto) {
		ModelMapper mapper = new ModelMapper();
		User user = mapper.map(userDto, User.class);
		return user;
	}

	public UserResponseDto convertUserToRespDto(User user) {
		ModelMapper mapper = new ModelMapper();
		UserResponseDto userResp = mapper.map(user, UserResponseDto.class);
		return userResp;
	}

	public BooksDto convertBooksToBooksDto(Books books) {
		ModelMapper mapper = new ModelMapper();
		BooksDto userResp = mapper.map(books, BooksDto.class);
		return userResp;
	}

	public List<BooksDto> convertBooksListToBooksDtoList(List<Books> bookList) {
		List<BooksDto> booksDtoList = bookList.stream().map(book -> convertBooksToBooksDto(book))
				.collect(Collectors.toList());
		return booksDtoList;
	}
}
