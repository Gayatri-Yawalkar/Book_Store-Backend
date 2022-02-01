package com.bridgelabz.bookstore.service;


import com.bridgelabz.bookstore.model.Wishlist;

public interface IWishlistService {
	public Wishlist addToWishlist(String token,int bookId);
}
