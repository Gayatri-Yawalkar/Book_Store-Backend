package com.bridgelabz.bookstore.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bridgelabz.bookstore.model.Books;
import com.bridgelabz.bookstore.model.Wishlist;
import com.bridgelabz.bookstore.service.WishlistServiceImpl;

@RestController
@RequestMapping("/wishlist")
@CrossOrigin(originPatterns = "*")
public class WishlistController {
	
	@Autowired
	private WishlistServiceImpl wishlistService;

	@PostMapping("/add-to-wishlist/{token}/{bookId}")
	public Wishlist addToCart(@PathVariable String token, @PathVariable int bookId) {
		Wishlist wishlist= wishlistService.addToWishlist(token, bookId);
		return wishlist;
	}
	@GetMapping("get-wishlist-product/{token}")
	public List<Books> getWishlistItems(@PathVariable String token) {
		List<Books> books = wishlistService.showItemsInWishlist(token);
		return books;
	}
	@PostMapping("/remove-from-wishlist/{token}/{bookId}")
	public List<Books> removeWishlistItems(@PathVariable String token, @PathVariable int bookId) {
		List<Books> books=wishlistService.removeItemFromWishlist(token, bookId);
		return books;
	}
}
