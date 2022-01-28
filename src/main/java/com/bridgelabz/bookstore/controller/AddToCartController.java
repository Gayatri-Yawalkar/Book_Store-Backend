package com.bridgelabz.bookstore.controller;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.http.HttpStatus;
        import org.springframework.http.ResponseEntity;
        import org.springframework.web.bind.annotation.PostMapping;
        import org.springframework.web.bind.annotation.RequestBody;
        import org.springframework.web.bind.annotation.RequestHeader;
        import org.springframework.web.bind.annotation.RequestMapping;
        import org.springframework.web.bind.annotation.RestController;

        import com.bridgelabz.bookstoreapp.dto.BookDetailsDto;
        import com.bridgelabz.bookstoreapp.dto.CartDto;
        import com.bridgelabz.bookstoreapp.dto.ResponseDTO;
        import com.bridgelabz.bookstoreapp.model.BookDetails;
        import com.bridgelabz.bookstoreapp.model.Cart;
        import com.bridgelabz.bookstoreapp.repository.CartRepository;
        import com.bridgelabz.bookstoreapp.service.AddToCartService;

        import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/cart")
@Slf4j
public class AddToCartController<AddToCartService> {

    @Autowired
    private AddToCartService addToCartService;

    @Autowired
    CartRepository cartRepo;

    @PostMapping("/addcart")
    ResponseEntity<UserResponseDTO> addToCart(@RequestBody CartDto cartDto) {
        Cart addCart = addToCartService.addToCart(cartDto);
        ResponseDTO dto = new ResponseDTO("All Books Retrieved successfully:", addCart);
        return new ResponseEntity<>(dto, HttpStatus.OK);


    }
}