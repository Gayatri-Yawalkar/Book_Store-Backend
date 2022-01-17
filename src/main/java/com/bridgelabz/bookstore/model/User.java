package com.bridgelabz.bookstore.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
@Data
@Entity
@Table(name="book_store_db")
public class User {
	@Id
	private String emailId;
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int userId;
	@Column(name="full_name")
	private String fullName;
	
	private String password;
	private long mobileNo;
	

}
