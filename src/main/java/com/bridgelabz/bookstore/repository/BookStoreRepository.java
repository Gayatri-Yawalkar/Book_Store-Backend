package com.bridgelabz.bookstore.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.bridgelabz.bookstore.model.User;
public interface BookStoreRepository extends JpaRepository<User,Integer>{
	User findByEmailId(String emailId);
}
