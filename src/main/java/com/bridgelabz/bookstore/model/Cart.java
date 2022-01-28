package com.bridgelabz.bookstore.model;

        import java.util.List;
        import java.util.Optional;

        import javax.persistence.*;

        import com.bridgelabz.bookstore.dto.BooksDto;
        import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

        import lombok.AllArgsConstructor;
        import lombok.Data;
        import lombok.NoArgsConstructor;

@Entity
@Table(name = "Cart")
@Data

@AllArgsConstructor
public class Cart {


    @Id
    @Column(name = "cart_id")
    private int cartId;
    public String bookName;
    public String bookAuthor;
    public String bookDescription;
    public String bookLogo;
    public int bookPrice;
    private int quantity;

//    private BookDetailsDto bookDetailsDto;

    @OneToOne(mappedBy="cart")
    private UserRegistrationData user;
    //
    @OneToOne(mappedBy="cart")
    private Orders order;

//    @OneToMany(cascade = CascadeType.ALL)
//    @JoinColumn(name = "cartId_fk",referencedColumnName = "cart_id")
//    private List<BookDetails> bookDetailsListcart;
    @OneToMany(mappedBy = "cart",cascade = CascadeType.ALL);
    private List<Books> bookList;


//
//	    @ManyToOne(fetch = FetchType.LAZY)
//	    @JoinColumn(name = "userId")
//	    private UserRegistrationData userRegistrationData;



//	    @OneToOne(fetch = FetchType.LAZY)
//	    @JoinColumn(name = "bookId")
//	    private Optional<BookDetails>  bookDetails;
//
//
//
//
//	    public Cart(UserRegistrationData userRegistrationData, Optional<BookDetails>  bookDetails, int quantity) {
//	        this.userRegistrationData = userRegistrationData;
//	        this.bookDetails = bookDetails;
//	        this.quantity = quantity;
//	    }

//	    public Cart() {
//
//	    }
}