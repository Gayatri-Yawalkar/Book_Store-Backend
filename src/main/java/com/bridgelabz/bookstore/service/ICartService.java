package com.bridgelabz.bookstore.service;

import com.bridgelabz.bookstore.dto.CartDto;
import com.bridgelabz.bookstore.model.Cart;

public interface ICartService {

	public Cart addProductToCart(String token, int bookId, CartDto cartDto);
}
