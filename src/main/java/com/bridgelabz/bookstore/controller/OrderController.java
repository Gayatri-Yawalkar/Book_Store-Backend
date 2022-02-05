package com.bridgelabz.bookstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.bookstore.model.Order;
import com.bridgelabz.bookstore.service.OrderServiceImpl;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@CrossOrigin(originPatterns = "*")
@RequestMapping("/order")
public class OrderController {

	@Autowired 
	private OrderServiceImpl orderService;
	
	@PostMapping("/place-order/{token}/{totalPrice}")
	public Order placeOrder(@PathVariable String token, @PathVariable double totalPrice) {
		Order placeOrder = orderService.placeOrder(token, totalPrice);
		return placeOrder;
	}
	
	@GetMapping("/get-all-orders/{token}")
	public List<Order> getAllOrders( @PathVariable String token){
		return orderService.getAllOrders(token);
	}
}
