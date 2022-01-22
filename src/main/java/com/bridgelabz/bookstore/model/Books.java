package com.bridgelabz.bookstore.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="book_data")
public class Books {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="book_id")
	private int bookId;
	
	@Column(name="book_name")
	private String bookName;
	
	@Column(name="book_bookAuthor")
	private String bookAuthor;
	
	@Column(name="book_price")
	private double bookPrice;
	
	@Column(name="book_rating")
	private int bookRating;
	
	@Column(name="available_book_quantity")
	private int availableBookQuantity;
	
	@Column(name="book_description")
	private String bookDescription;
	
	@Column(name="book_image")
	private String bookImages;
}
