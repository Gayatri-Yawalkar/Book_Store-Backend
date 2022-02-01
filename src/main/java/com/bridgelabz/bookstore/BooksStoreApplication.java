package com.bridgelabz.bookstore;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.bridgelabz.bookstore.model.Books;
import com.bridgelabz.bookstore.model.Cart;
import com.bridgelabz.bookstore.model.User;
import com.bridgelabz.bookstore.repository.BookRepository;
import com.bridgelabz.bookstore.repository.CartRepository;
import com.bridgelabz.bookstore.repository.UserRepository;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "User Mangement Api", version = "1.0", description = "Api Description"))
public class BooksStoreApplication implements CommandLineRunner {

	@Autowired
	private CartRepository cartRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private BookRepository bookRepo;
	
	public static void main(String[] args) {
		SpringApplication.run(BooksStoreApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		User user = new User();
		user.setUserId(777);
		user.setEmailId("jpademo@gmail.com");
		user.setFullName("jpa");
		user.setMobileNo(4545);
		user.setPassword("hfdda");
		
		Books book = new Books();
		book.setBookId(7777);
		book.setBookName("amit");
		book.setBookAuthor("by amit");
		book.setAvailableBookQuantity(15);
		book.setBookDescription("book desc");
		book.setBookImages("images");
		book.setBookPrice(2562);
		book.setBookRating(4);
		
		
		Books book1 = new Books();
		book1.setBookId(7777);
		book1.setBookName("Room no 101");
		book1.setBookAuthor("by Gayatri");
		book1.setAvailableBookQuantity(15);
		book1.setBookDescription("book desc");
		book1.setBookImages("images");
		book1.setBookPrice(2562);
		
		List<Books> books = new ArrayList<>();
		books.add(book);
		books.add(book1);
		
		Cart cart = new Cart();
		cart.setId(77777);
		cart.setBooks(books);
		cart.setUser(user);
		
		user.setCart(cart);
		
//		cartRepo.save(cart);
	
		
		
	
		
	}

}
