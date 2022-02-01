package com.bridgelabz.bookstore.service;

import com.bridgelabz.bookstore.dto.CartDto;
import com.bridgelabz.bookstore.model.Cart;

import java.util.List;

public interface ICartService {

	public Cart addProductToCart(String token, int bookId, CartDto cartDto);
}
	void deleteFromCart(int bookId);

	Cart updateQuantity(String token, int bookId, CartDto cartDto);

	List<Cart> findAllInCart(String token);

	Cart deleteFromController(String token, int bookId, CartDto cartDto);
}