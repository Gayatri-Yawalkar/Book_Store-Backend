package com.bridgelabz.bookstore.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.bookstore.model.Books;
import com.bridgelabz.bookstore.model.Cart;
import com.bridgelabz.bookstore.model.Order;
import com.bridgelabz.bookstore.model.User;
import com.bridgelabz.bookstore.repository.CartRepository;
import com.bridgelabz.bookstore.repository.OrderRepository;
import com.bridgelabz.bookstore.repository.UserRepository;
import com.bridgelabz.bookstore.utilis.JwtUtil;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OrderServiceImpl implements IOrderService{
	
	@Autowired 
	JwtUtil jwt;
	
	@Autowired 
	UserServiceImpl userService;
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired 
	CartServiceImpl cartService;
	
	@Autowired
	CartRepository cartRepo;
	
	@Autowired
	BookService bookService;
	
	@Autowired
	OrderRepository orderRepo;
	
	@Override
	public Order placeOrder(String token, double totalPrice) {
		
		String email = jwt.getEmailFromToken(token);
		
		User user = userService.getUserByEmailId(email);
		
		Cart cart = cartRepo.findByUserId(user.getUserId());
		
		List<Books> books = cart.getBooks();
		
		//books.stream().forEach(b->b.setAvailableBookQuantity(b.getAvailableBookQuantity()-b.getInCartQuantity()));
		
		Iterator<Books> itr = books.iterator();
		
		List<Books> copyBook = new ArrayList<>();
		while(itr.hasNext()) {
			copyBook.add(itr.next());
		}
		
		Order order = new Order();
		order.setCart(cart);
		order.setUser(user);
		order.setTotalPrice(totalPrice);
		order.setPlacedAt(LocalDate.now());
		order.setBooks(copyBook);
		
		Order savedOrder = orderRepo.save(order);
		
		for(Books b: books) {
			b.setAvailableBookQuantity(b.getAvailableBookQuantity()-b.getInCartQuantity());
			b.setInCartQuantity(0);
		}
		
		List<Books> updateBooksQuantity = bookService.updateBooksQuantity(books);
		
		List<Books> emptyList = new ArrayList<>();
		cart.setBooks(emptyList);
		user.setCart(cart);
		cartRepo.save(cart);
		userRepo.save(user);
		return savedOrder;
	}

	@Override
	public List<Order> getAllOrders(String token) {
		
		String email = jwt.getEmailFromToken(token);
		User user = userService.getUserByEmailId(email);
		List<Order> allOrder = orderRepo.getAllOrder(user.getUserId());
		
		return allOrder;
	}


}
