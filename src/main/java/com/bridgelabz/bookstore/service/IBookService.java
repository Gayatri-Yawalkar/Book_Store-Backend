package com.bridgelabz.bookstore.service;

import java.util.List;

import com.bridgelabz.bookstore.model.Books;

public interface IBookService {

	
	public Books displaySingleBook(int bookId);
	
	public List<Books> getAllBooks();
}
