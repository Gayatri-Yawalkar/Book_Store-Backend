package com.bridgelabz.bookstore.repository;
        import com.bridgelabz.bookstore.model.BookDetails;
        import org.springframework.data.jpa.repository.JpaRepository;

public interface BookDetailsRepository extends JpaRepository <BookDetails,Integer>{
}