package com.bridgelabz.bookstore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
	
	public List<Books> updateBooksQuantity(List<Books> books){
		List<Books> savedBooks = bookRepository.saveAll(books);
		return savedBooks;
}
	@Override
	public List<Books> getAllBooks(int pageNo) {
		log.info("Finding all books");
		Pageable page=PageRequest.of(pageNo-1,8);
		Page<Books> pageBooks = bookRepository.findAll(page);
		List<Books> allBooks=pageBooks.getContent();
		if (allBooks.isEmpty()) {
			log.error("Book List is Empty");
			throw new BookStoreException("Book List is empty");
		}
		log.info("Found Books");
		return allBooks;
	}
	
	@Override
	public List<Books> getSortedByPriceBooks(int pageNo) {
		log.info("Finding all books");
		Pageable page=PageRequest.of(pageNo-1,4,Sort.by("bookPrice"));
		Page<Books> pageBooks = bookRepository.findAll(page);
		List<Books> allBooks=pageBooks.getContent();
		if (allBooks.isEmpty()) {
			log.error("Book List is Empty");
			throw new BookStoreException("Book List is empty");
		}
		log.info("Found Books");
		return allBooks;
	}
	
	@Override
	public List<Books> getSortedByPriceBooksDesc(int pageNo) {
		log.info("Finding all books");
		Pageable pageDesc=PageRequest.of(pageNo-1,4,Sort.by("bookPrice").descending());
		Page<Books> pageBooks = bookRepository.findAll(pageDesc);
		List<Books> allBooks=pageBooks.getContent();
		if (allBooks.isEmpty()) {
			log.error("Book List is Empty");
			throw new BookStoreException("Book List is empty");
		}
		log.info("Found Books");
		return allBooks;

	}
}
