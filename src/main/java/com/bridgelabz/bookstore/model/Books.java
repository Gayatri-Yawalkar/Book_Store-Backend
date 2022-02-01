package com.bridgelabz.bookstore.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;

import org.springframework.boot.context.properties.bind.DefaultValue;

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
	
	@Column(name="book_Author")
	private String bookAuthor;
	
	@Column(name="book_price")
	private double bookPrice;
	
	@Column(name="book_rating")
	private int bookRating;
	
	@Column(name="available_book_quantity")
	private int availableBookQuantity;
	
	@Column(name="book_quantity_in_cart")
	private Integer inCartQuantity = 0;
	
	@Column(name="book_description")
	private String bookDescription;
	
	@Column(name="book_image")
	private String bookImages;
	
}
