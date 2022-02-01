package com.bridgelabz.bookstore.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.bridgelabz.bookstore.repository.UserRepository;
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
		if (userByEmail != null) {

			Books bookById = bookService.displaySingleBook(bookId);

			if (bookById != null) {

				Cart cartByUserId = cartRepo.findByUserId(userByEmail.getUserId());

				if (cartByUserId != null) {
					List<Books> books = cartByUserId.getBooks();

					if (books.contains(bookById)) {
						List<Books> matchedBook = books.stream()
								.filter(book -> book.getBookId() == bookById.getBookId()).collect(Collectors.toList());
						Books inCartBook = matchedBook.get(0);
						inCartBook.setInCartQuantity(inCartBook.getInCartQuantity() + 1);
						cartByUserId.setWholeCartQuantity(cartByUserId.getWholeCartQuantity() + 1);
					} else {
						bookById.setInCartQuantity(1);
						books.add(bookById);
						cartByUserId.setWholeCartQuantity(cartByUserId.getWholeCartQuantity() + 1);
					}
					userByEmail.setCart(cartByUserId);

					Cart updatedCart = cartRepo.save(cartByUserId);

					return updatedCart;

				} else {
					List<Books> bookList = new ArrayList<>();
					bookById.setInCartQuantity(1);
					bookList.add(bookById);

					Cart cart = new Cart();
					cart.setWholeCartQuantity(1);
					cart.setBooks(bookList);
					cart.setUser(userByEmail);

					userByEmail.setCart(cart);

					Cart save = cartRepo.save(cart);
					return save;
				}
			} else {
				throw new BookStoreException("Book Not Found ");
			}
		} else {
			throw new BookStoreException("User not valid ");
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
//				throw new BookStoreException("Your Cart is Empty");
				List<Books> newBooks = new ArrayList<>();
				return newBooks;
			}
		} else {
			throw new BookStoreException("User not found.");
		}
		@Override
		public void deleteFromCart(int bookId) {
			cartRepo.deleteById(bookId);
		}

		@Override
		public Cart updateQuantity(String token, int bookId, CartDto cartDto)) {
			int id = Math.toIntExact(tokenUtil.decodeToken(token));
			Optional<User> isPresent = UserRepository.findById(id);
			if(isPresent.isPresent()){
				Cart cart = CartRepository.getById(cartId);
				cart.setQuantity(cartdto.quantity);
//            BookDetails book =bookService.getBookByIdToken(token,cartdto.bookId);
//            cart.setBook(book);
				return CartRepository.save(cart);
			}
			return  null;
		}

		@Override
		public List<Cart> findAllInCart(String token) {
			int id = Math.toIntExact(tokenUtil.decodeToken(token));
			Optional<User> isPresent = UserRepository.findById(id);
			if(isPresent.isPresent()){
				List<Cart> cartItems = cartRepo.findAllCartsByUserId(id);
				return  cartItems;
			}
			return null;
		}
	}

	}

}
