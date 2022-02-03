package com.bridgelabz.bookstore.dto;

import java.util.List;

import com.bridgelabz.bookstore.model.Books;

import lombok.Data;

@Data
public class ItemsResponse {

	private Integer itemsQuantity;
	private List<Books> books;
}
