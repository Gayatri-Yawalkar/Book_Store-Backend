package com.bridgelabz.bookstore.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;

@Entity
@Data
public class Wishlist {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="wishlist_id")
	private Integer wishlistId;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	@JsonBackReference
	private User user;

	@OneToMany(cascade = CascadeType.ALL)
	private List<Books> books = new ArrayList<>();

	@Column(name = "quantity_in_wishlist")
	private Integer wholeWishlistQuantity = 0;

	
}
