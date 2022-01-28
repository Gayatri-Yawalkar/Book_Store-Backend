package com.bridgelabz.bookstore.service;

        import com.bridgelabz.bookstore.repository.BookDetailsRepository;
        import org.modelmapper.ModelMapper;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.context.annotation.ComponentScan;
        import org.springframework.stereotype.Service;

        import com.bridgelabz.bookstore.dto.BookDetailsDto;
        import com.bridgelabz.bookstore.dto.CartDto;
        import com.bridgelabz.bookstore.model.BookDetails;
        import com.bridgelabz.bookstore.model.Cart;
        import com.bridgelabz.bookstore.model.UserRegistrationData;
        import com.bridgelabz.bookstore.repository.CartRepository;
        import com.bridgelabz.bookstore.repository.UserRegistrationRepository;

        import java.util.Optional;

@ComponentScan({"com.bridgelabz.bookstore"})
@Service
public class AddToCartService implements IAddToCartService{


    @Autowired
    BookDetailsRepository bookrepo;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    BookDetailsService  bookDetailsService;




    @Autowired
    UserRegistrationRepository userRegistrationRepo;




//    @Override
//    public BookDetailsDto addToCart(BookDetailsDto bookDetailsDto) {
//        ModelMapper modelMapper=new ModelMapper();
//        Cart addDetails = modelMapper.map(bookDetailsDto, Cart.class);
//        cartRepository.save(addDetails);
//        return bookDetailsDto;
//    }


    @Override
    public Cart addToCart(CartDto cartDTO) {

        return null;
    }
}