package com.bridgelabz.bookstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bridgelabz.bookstore.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer>{

	@Query(value = "select * from user_orders where user_id =:userId", nativeQuery = true)
	List<Order> getAllOrder(int userId);

}
