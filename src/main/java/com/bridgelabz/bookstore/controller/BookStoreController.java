package com.bridgelabz.bookstore.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bridgelabz.bookstore.converter.DtoToEntityConverter;
import com.bridgelabz.bookstore.dto.UserResponseDto;
import com.bridgelabz.bookstore.model.User;
import com.bridgelabz.bookstore.service.BookStoreService;
@RestController
@CrossOrigin(allowedHeaders="*",origins="*")
@RequestMapping("/bookstore")
public class BookStoreController {
	@Autowired
	private BookStoreService bookStoreService;
	@Autowired
	private DtoToEntityConverter dtoToEntityConverter;
	@GetMapping(value="/getuser/{emailId}/{password}",produces="application/json")
	public UserResponseDto getUserData(@PathVariable("emailId") String emailId,@PathVariable("password") String password) {
		User user=bookStoreService.getUserDataByEmailId(emailId,password);
		UserResponseDto respDto=dtoToEntityConverter.convertUserToRespDto(user);
		return respDto;
	}
}
