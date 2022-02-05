package com.bridgelabz.bookstore.service;

import java.util.List;

import com.bridgelabz.bookstore.model.Order;

public interface IOrderService {

	Order placeOrder(String token, double totalPrice);
	
	List<Order> getAllOrders(String token);
}
