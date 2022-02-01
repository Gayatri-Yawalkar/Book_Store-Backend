package com.bridgelabz.bookstore.dto;

import java.util.List;

import com.bridgelabz.bookstore.model.Books;

import lombok.Data;

@Data
public class CartResponse {

	private Integer cartQuantity;
	private List<Books> books;
}
