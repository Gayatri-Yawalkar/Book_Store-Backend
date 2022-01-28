package com.bridgelabz.bookstore.service;
        import java.util.List;

        import com.bridgelabz.bookstore.dto.BookDetailsDto;
        import com.bridgelabz.bookstore.dto.CartDto;
        import com.bridgelabz.bookstore.model.BookDetails;
        import com.bridgelabz.bookstore.model.Cart;

public interface IAddToCartService {

    Cart addToCart(CartDto cartDTO);

}