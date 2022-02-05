package com.bridgelabz.bookstore.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

	@GetMapping("/getbook/{bookId}")
	public BooksDto getOneBook(@PathVariable int bookId) {
		Books getOneBook = bookService.displaySingleBook(bookId);
		BooksDto respDto = converter.convertBooksToBooksDto(getOneBook);
		log.info("Retrive single book by Id " + respDto);
		return respDto;
	}

	@GetMapping("/getbooks")
	public List<BooksDto> getAllBooks() {
		List<Books> allBooks = bookService.getAllBooks();
		List<BooksDto> booksDtoList = converter.convertBooksListToBooksDtoList(allBooks);
		return booksDtoList;
	}
	
	@GetMapping("/get-books/{pageNo}")
	public List<BooksDto> getAllBooks(@PathVariable int pageNo) {
		List<Books> allBooks = bookService.getAllBooks(pageNo);
		List<BooksDto> booksDtoList = converter.convertBooksListToBooksDtoList(allBooks);
		return booksDtoList;
	}
	
	@GetMapping("/get-sorted-books/{pageNo}")
	public List<BooksDto> getSortedBooks(@PathVariable int pageNo) {
		List<Books> allBooks = bookService.getSortedByPriceBooks(pageNo);
		List<BooksDto> booksDtoList = converter.convertBooksListToBooksDtoList(allBooks);
		return booksDtoList;
	}
	
	@GetMapping("/get-desc-sorted-books/{pageNo}")
	public List<BooksDto> getDescSortedBooks(@PathVariable int pageNo) {
		List<Books> allBooks = bookService.getSortedByPriceBooksDesc(pageNo);
		List<BooksDto> booksDtoList = converter.convertBooksListToBooksDtoList(allBooks);
		return booksDtoList;
	}

}
