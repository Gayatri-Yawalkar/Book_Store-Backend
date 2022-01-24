package com.bridgelabz.bookstore.dto;

import lombok.Data;

@Data
public class BooksDto {

	private int bookId;
	private String bookName;
	private String bookAuthor;
	private double bookPrice;
	private int bookRating;
	private int availableBookQuantity;
	private String bookDescription;
	private String bookImages;
}
