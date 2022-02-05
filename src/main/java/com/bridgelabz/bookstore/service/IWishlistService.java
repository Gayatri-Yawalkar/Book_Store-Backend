package com.bridgelabz.bookstore.service;

import java.util.List;
import com.bridgelabz.bookstore.model.Books;
import com.bridgelabz.bookstore.model.Wishlist;

public interface IWishlistService {
	public Wishlist addToWishlist(String token,int bookId);
	public List<Books> showItemsInWishlist(String token);
	public List<Books> removeItemFromWishlist(String token,int bookId);
}
