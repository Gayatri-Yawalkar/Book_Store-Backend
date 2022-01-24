package com.bridgelabz.bookstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.bookstore.exception.BookStoreException;
import com.bridgelabz.bookstore.model.Books;
import com.bridgelabz.bookstore.model.User;
import com.bridgelabz.bookstore.repository.BookRepository;
import com.bridgelabz.bookstore.repository.UserRepository;
import com.bridgelabz.bookstore.utilis.JwtUtil;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BookService implements IBookService {
	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private JwtUtil jwt;

	@Override
	public Books displaySingleBook(int bookId, String token) {
		log.info("Getting email from the token" + token);
		String email = jwt.getEmailFromToken(token);
		User user = userRepository.findByEmailId(email);
		if (user != null) {
			log.info("User found in our system with above email.");
			return bookRepository.findById(bookId)
					.orElseThrow(() -> new BookStoreException("This Book does not exist..!"));
		} else {
			log.error("User not found in our system with email : " + email);
			throw new BookStoreException("User not found for This Email");
		}

	}
}
