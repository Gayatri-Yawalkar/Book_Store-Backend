package com.bridgelabz.bookstore.model;
        import java.io.Serializable;
        import java.time.LocalDate;

        import javax.persistence.*;

        import lombok.AllArgsConstructor;
        import lombok.Data;
        import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "orders")
@NoArgsConstructor
@AllArgsConstructor
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_Id")
    private int orderId;

    @Column(name="number_book_order")
    private String numberOfBooksOrdered;

    @Column(name="book_total_price")
    private String totalPrice;

    private LocalDate orderDate;
    private int price;
    private int quantity;
    private String address;
    private int Id;
    private int bookId;
    //
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="orderFk")
    private Cart cart;


}
