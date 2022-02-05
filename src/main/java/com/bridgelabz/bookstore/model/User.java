package com.bridgelabz.bookstore.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;
import lombok.ToString;

@Data
@Entity
@ToString(exclude = "cart")
@Table(name = "user_data", uniqueConstraints = @UniqueConstraint(columnNames = "email_id"))
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int userId;

	@Column(name = "email_id")
	private String emailId;

	@Column(name = "full_name")
	private String fullName;

	@Column(name = "password")
	private String password;

	@Column(name = "mobile_no")
	private long mobileNo;

	@Column(name = "created_at_time")
	private LocalDateTime createdAt;

	@Column(name = "updated_at_time")
	private LocalDateTime updatedAt;

	@JsonManagedReference
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
	private Cart cart;
	
	@JsonManagedReference
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
	private Wishlist wishlist;
	
	@JsonBackReference
	@OneToMany(mappedBy = "user")
	private List<Order> order;
}
