package com.bridgelabz.bookstore.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bridgelabz.bookstore.dto.CartDto;
import com.bridgelabz.bookstore.dto.ItemsResponse;
import com.bridgelabz.bookstore.model.Books;
import com.bridgelabz.bookstore.model.Cart;
import com.bridgelabz.bookstore.service.CartServiceImpl;

@RestController
@RequestMapping("/cart")
@CrossOrigin(originPatterns = "*")
public class CartController {

	@Autowired
	private CartServiceImpl cartService;

	@PostMapping("/add-to-cart/{token}/{bookId}")
	public Cart addToCart(@PathVariable String token, @PathVariable int bookId, @RequestBody CartDto cartDto) {
		Cart addProductToCart = cartService.addProductToCart(token, bookId, cartDto);
		return addProductToCart;
	}

	@GetMapping("/get-cart-product/{token}")
	public ItemsResponse getCartProduct(@PathVariable String token) {
		List<Books> books = cartService.showProductsInCarts(token);
		ItemsResponse response = new ItemsResponse();
		response.setBooks(books);
		response.setItemsQuantity(books.size());
		return response;
	}
}
