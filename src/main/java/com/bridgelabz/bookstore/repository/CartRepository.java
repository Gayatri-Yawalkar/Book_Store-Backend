package com.bridgelabz.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bridgelabz.bookstore.model.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer>{

	@Query(value= "select * from cart where user_id= :userId", nativeQuery = true)
	Cart findByUserId(int userId);
}
