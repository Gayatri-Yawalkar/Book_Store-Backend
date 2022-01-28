package com.bridgelabz.bookstore.model;
        import com.bridgelabz.bookstoreapp.dto.UserRegistrationDto;

        import lombok.Data;

        import javax.persistence.*;
        import java.time.LocalDate;
        import java.util.List;


@Entity
@Table(name = "bookstore")
@Data
public class UserRegistrationData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int Id;
    @Column(name = "full_name")
    private String fullName;
    @Column(name = "email_id")
    private String emailId;
    @Column(name = "password")
    private String password;
    @Column(name = "mobile_no")
    private String mobileNo;
    @Column(name ="update_date")
    private LocalDate updatedDate;
    @Column(name = "registerd_date")
    private  LocalDate registerDate;
    @Column(name = "status")
    private String status;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="cartFk")
    private Cart cart;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "bookfk",referencedColumnName = "id")
    private List<BookDetails> bookDetailsList;

    public UserRegistrationData() {

    }

    public void createUser(UserRegistrationDto userDTO) {
        this.fullName = userDTO.fullName;
        this.emailId = userDTO.emailId;
        this.password = userDTO.password;
        this.mobileNo = userDTO.mobileNo;
        this.updatedDate =userDTO.updatedDate;
        this.registerDate=userDTO.registerDate;
        this.status=userDTO.status;

    }

//    public void updateUser(UserRegistrationDto userDTO) {
//        this.fullName = userDTO.fullName;
//        this.emailId = userDTO.emailId;
//        this.password = userDTO.password;
//        this.mobileNo = userDTO.mobileNo;
//        this.updatedDate =userDTO.updatedDate;
//        this.registerDate=userDTO.registerDate;
//        this.status=userDTO.status;
//
//    }



}