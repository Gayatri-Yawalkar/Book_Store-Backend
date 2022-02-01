package com.bridgelabz.bookstore.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.bridgelabz.bookstore.model.Wishlist;
@Repository
public interface WishlistRepository extends JpaRepository<Wishlist,Integer> {
	
	@Query(value= "select * from wishlist where user_id= :userId", nativeQuery = true)
	Wishlist findByUserId(int userId);

}
