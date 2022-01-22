package com.bridgelabz.bookstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.bookstore.converter.Converter;
import com.bridgelabz.bookstore.repository.BookRepository;


@Service
public class BookService {
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private Converter converter;
	
}
