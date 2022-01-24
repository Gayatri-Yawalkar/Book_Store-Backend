package com.bridgelabz.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.bookstore.converter.Converter;
import com.bridgelabz.bookstore.dto.BooksDto;
import com.bridgelabz.bookstore.model.Books;
import com.bridgelabz.bookstore.service.BookService;

import lombok.extern.slf4j.Slf4j;
@RestController
@Slf4j
@CrossOrigin(originPatterns = "*")
@RequestMapping("/books")
public class BookController {
	
	@Autowired
	private BookService bookService;
	@Autowired
	private Converter converter;

    @GetMapping("/getBook/{bookId}")
    public BooksDto getOneBook(@RequestHeader(name = "token") String token,@PathVariable int bookId)
    {
    	Books getOneBook = bookService.displaySingleBook(bookId,token);
		BooksDto respDto = converter.convertBooksToBooksDto(getOneBook);
		log.info("Retrive single book by Id "+respDto);
		return respDto;
     
    }   


}
