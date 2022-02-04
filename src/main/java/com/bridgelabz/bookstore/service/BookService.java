package com.bridgelabz.bookstore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.bookstore.exception.BookStoreException;
import com.bridgelabz.bookstore.model.Books;
import com.bridgelabz.bookstore.repository.BookRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BookService implements IBookService {
	@Autowired
	private BookRepository bookRepository;

	@Override
	public Books getSingleBook(int bookId) {
		log.info("Finding book with book Id : " + bookId);
		return bookRepository.findById(bookId)
				.orElseThrow(() -> new BookStoreException("Book Not Found wih Book Id : " + bookId));
	}

	@Override
	public List<Books> getAllBooks() {
		log.info("Finding all books");
		List<Books> allBooks = bookRepository.findAll();
		if (allBooks.isEmpty()) {
			log.error("Book List is Empty");
			throw new BookStoreException("Book List is empty");
		}
		log.info("Found Books");
		return allBooks;
	}
}
