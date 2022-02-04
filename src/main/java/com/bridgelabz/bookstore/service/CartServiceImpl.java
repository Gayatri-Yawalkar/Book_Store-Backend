package com.bridgelabz.bookstore.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.bookstore.dto.CartDto;
import com.bridgelabz.bookstore.exception.BookStoreException;
import com.bridgelabz.bookstore.model.Books;
import com.bridgelabz.bookstore.model.Cart;
import com.bridgelabz.bookstore.model.User;
import com.bridgelabz.bookstore.repository.CartRepository;
import com.bridgelabz.bookstore.utilis.JwtUtil;

@Service
public class CartServiceImpl implements ICartService {

	@Autowired
	JwtUtil jwt;

	@Autowired
	private UserServiceImpl userService;

	@Autowired
	private BookService bookService;

	@Autowired
	private CartRepository cartRepo;

	@Override
	public Cart addProductToCart(String token, int bookId, CartDto cartDto) {

		String emailFromToken = jwt.getEmailFromToken(token);

		User userByEmail = userService.getUserByEmailId(emailFromToken);

		Books bookById = bookService.getSingleBook(bookId);

		if (bookById != null) {

			Cart cartByUserId = cartRepo.findByUserId(userByEmail.getUserId());

			if (cartByUserId != null) {
				List<Books> books = cartByUserId.getBooks();

				Optional<Books> matchedBook = books.stream().filter(book -> book.getBookId() == bookById.getBookId())
						.findFirst();

				if (matchedBook.isPresent()) {
					Books inCartBook = matchedBook.get();
					inCartBook.setInCartQuantity(inCartBook.getInCartQuantity() + 1);
				} else {
					bookById.setInCartQuantity(1);
					books.add(bookById);
				}
				userByEmail.setCart(cartByUserId);

				Cart updatedCart = cartRepo.save(cartByUserId);

				return updatedCart;

			} else {
				List<Books> bookList = new ArrayList<>();
				bookById.setInCartQuantity(1);
				bookList.add(bookById);

				Cart cart = new Cart();
				cart.setBooks(bookList);
				cart.setUser(userByEmail);

				userByEmail.setCart(cart);

				Cart save = cartRepo.save(cart);
				return save;
			}
		} else {
			throw new BookStoreException("Book Not Found ");
		}
	}

	public List<Books> showProductsInCarts(String token) {
		String email = jwt.getEmailFromToken(token);

		User user = userService.getUserByEmailId(email);

		if (user != null) {

			Cart cart = cartRepo.findByUserId(user.getUserId());

			if (cart != null) {
				List<Books> books = cart.getBooks();
				return books;
			} else {
				// throw new BookStoreException("Your Cart is Empty");
				List<Books> newBooks = new ArrayList<>();
				return newBooks;
			}
		} else {
			throw new BookStoreException("User not found.");
		}
	}

	public List<Books> removeBook(String token, int bookId) {
		String email = jwt.getEmailFromToken(token);

		User user = userService.getUserByEmailId(email);

		Cart cart = cartRepo.findByUserId(user.getUserId());

		if (cart != null) {
			List<Books> books = cart.getBooks();

			Books book = bookService.getSingleBook(bookId);

			if (books.contains(book)) {
				books.remove(book);
				cart.setBooks(books);
				cartRepo.save(cart);
				return books;
			} else {
				throw new BookStoreException("Book is not present in the cart");
			}

		} else {
			List<Books> books = new ArrayList<>();
			return books;
		}
	}

	public List<Books> decreaseOneQuantity(String token, int bookId) {

		String email = jwt.getEmailFromToken(token);
		User user = userService.getUserByEmailId(email);

		Cart cart = cartRepo.findByUserId(user.getUserId());

		if (cart != null) {
			Books book = bookService.getSingleBook(bookId);

			List<Books> books = cart.getBooks();

			Optional<Books> matchedBook = books.stream().filter(b -> b.getBookId() == book.getBookId()).findFirst();

			if (matchedBook.isPresent()) {
				Books inCartBook = matchedBook.get();
				inCartBook.setInCartQuantity(inCartBook.getInCartQuantity() - 1);
				cart.setBooks(books);
				cartRepo.save(cart);
				return books;

			} else {
				throw new BookStoreException("Book is not present in your Cart");
			}

		} else {
			throw new BookStoreException("Cart is already empty");
		}
	}

}
