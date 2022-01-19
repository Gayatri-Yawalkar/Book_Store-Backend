package com.bridgelabz.bookstore.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Data;
@Data
@Entity
<<<<<<< HEAD
@Table(name="book_store_db", uniqueConstraints = @UniqueConstraint(columnNames = "emailId"))
public class User {
	
	private String emailId;
=======
@Table(name="book_store_db",uniqueConstraints = @UniqueConstraint(columnNames="email_id"))
public class User {
>>>>>>> e0d7e3695546725e7afda7b4a31f4d4e012df86c
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int userId;
	
	@Column(name="email_id")
	private String emailId;
	
	@Column(name="full_name")
	private String fullName;
	
	@Column(name="password")
	private String password;
	
	@Column(name="mobile_no")
	private long mobileNo;
	

}
