package com.bridgelabz.bookstore.service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bridgelabz.bookstore.exception.BookStoreException;
import com.bridgelabz.bookstore.model.Books;
import com.bridgelabz.bookstore.model.User;
import com.bridgelabz.bookstore.model.Wishlist;
import com.bridgelabz.bookstore.repository.WishlistRepository;
import com.bridgelabz.bookstore.utilis.JwtUtil;
@Service
public class WishlistServiceImpl implements IWishlistService{
	
	@Autowired
	JwtUtil jwt;

	@Autowired
	private UserServiceImpl userService;

	@Autowired
	private BookService bookService;

	@Autowired
	private WishlistRepository wishlistRepository;
	
	public Wishlist addToWishlist(String token,int bookId) {
		User user=getUserFromToken(token);
		Books book=getBook(bookId);
		Wishlist wishlist=getWishList(user,book);
		return wishlist;
	}
	
	public List<Books> showItemsInWishlist(String token) {
		User user=getUserFromToken(token);
		Wishlist wishlist=wishlistRepository.findByUserId(user.getUserId());
		if (wishlist != null) {
			List<Books> books = wishlist.getBooks();
			return books;
		} else {
			List<Books> newBooks = new ArrayList<>();
			return newBooks;
		}
	}
	
	public List<Books> removeItemFromWishlist(String token,int bookId) {
		User user=getUserFromToken(token);
		Books book=getBook(bookId);
		Wishlist wishlist=wishlistRepository.findByUserId(user.getUserId());
		if(wishlist!=null) {
			List<Books> bookList=wishlist.getBooks();
			bookList.remove(book);
			wishlist.setWholeWishlistQuantity(wishlist.getWholeWishlistQuantity()-1);
			wishlist.setBooks(bookList);
			user.setWishlist(wishlist);
			Wishlist updatedWishlist=wishlistRepository.save(wishlist);
			return updatedWishlist.getBooks();
		} else {
			List<Books> newBooks = new ArrayList<>();
			return newBooks;
		}
	}
	
	public User getUserFromToken(String token) {
		String emailFromToken = jwt.getEmailFromToken(token);
		User user = userService.getUserByEmailId(emailFromToken);
		if (user != null) {
			return user;
		} else {
			throw new BookStoreException("User not valid ");
		}
	}
	public Books getBook(int bookId) {
		Books book=bookService.displaySingleBook(bookId);
		if(book!=null) {
			return book;
		} else {
			throw new BookStoreException("Book Not Found ");
		}
	}
	public Wishlist getWishList(User user,Books book) {
		Wishlist wishlist=wishlistRepository.findByUserId(user.getUserId());
		if(wishlist!=null) {
			return updateWishlist(wishlist,book,user);
		} else {
			return addNewWishlist(user,book);
		}
	}
	public Wishlist updateWishlist(Wishlist wishList,Books book,User user) {
		List<Books> books = wishList.getBooks();
		Optional<Books> bookFromList=books.stream().filter(b->b.getBookId()==book.getBookId()).findFirst();
		if(bookFromList.isPresent()) {
			throw new BookStoreException("Already Added to Wishlist");
		} else {
			books.add(book);
			wishList.setWholeWishlistQuantity(wishList.getWholeWishlistQuantity()+1);
			wishList.setBooks(books);
			user.setWishlist(wishList);
			Wishlist updatedWishlist=wishlistRepository.save(wishList);
			return updatedWishlist;
		}
	}
	public Wishlist addNewWishlist(User user,Books book) {
		List<Books> bookList = new ArrayList<>();
		bookList.add(book);
		Wishlist wishlist=new Wishlist();
		wishlist.setWholeWishlistQuantity(1);
		wishlist.setBooks(bookList);
		wishlist.setUser(user);
		user.setWishlist(wishlist);
		Wishlist newWishlist=wishlistRepository.save(wishlist);
		return newWishlist;
	}
}
