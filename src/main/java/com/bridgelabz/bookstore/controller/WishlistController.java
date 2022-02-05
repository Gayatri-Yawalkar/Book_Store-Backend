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
import com.bridgelabz.bookstore.dto.ItemsResponse;
import com.bridgelabz.bookstore.dto.WishlistDto;
import com.bridgelabz.bookstore.model.Books;
import com.bridgelabz.bookstore.model.Wishlist;
import com.bridgelabz.bookstore.service.WishlistServiceImpl;

@RestController
@RequestMapping("/wishlist")
@CrossOrigin(originPatterns = "*")
public class WishlistController {
	
	@Autowired
	private WishlistServiceImpl wishlistService;

	@PostMapping("/add-to-wishlist/{token}")
	public Wishlist addToWishlist(@PathVariable String token, @RequestBody WishlistDto wishlistDto) {
		Wishlist wishlist= wishlistService.addToWishlist(token,wishlistDto.getBookId());
		return wishlist;
	}
	@GetMapping("get-wishlist-product/{token}")
	public ItemsResponse getWishlistItems(@PathVariable String token) {
		List<Books> books = wishlistService.showItemsInWishlist(token);
		ItemsResponse cartResponse=new ItemsResponse();
		cartResponse.setBooks(books);
		cartResponse.setItemsQuantity(books.size());
		return cartResponse;
	}
	@PostMapping("/remove-from-wishlist/{token}")
	public ItemsResponse removeWishlistItems(@PathVariable String token, @RequestBody WishlistDto wishlistDto) {
		List<Books> books=wishlistService.removeItemFromWishlist(token, wishlistDto.getBookId());
		ItemsResponse itemsResponse=new ItemsResponse();
		itemsResponse.setBooks(books);
		itemsResponse.setItemsQuantity(books.size());
		return itemsResponse;
	}
}
