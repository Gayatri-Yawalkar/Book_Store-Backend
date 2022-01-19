package com.bridgelabz.bookstore.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.bridgelabz.bookstore.model.User;
@Repository
public interface UserRepository extends JpaRepository<User,Integer>{
	User findByEmailId(String emailId);
}
