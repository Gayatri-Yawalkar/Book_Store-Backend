package com.bridgelabz.bookstore.model;
import java.time.LocalDateTime;
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
@Table(name="book_store_db", uniqueConstraints = @UniqueConstraint(columnNames = "email_id"))
public class User {
	

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
	
	@Column(name="created_at_time")
	private LocalDateTime createdAt;
	
	@Column(name="updated_at_time")
	private LocalDateTime updatedAt;
	

}
