package com.bridgelabz.bookstore.dto;
import lombok.Data;

@Data
public class UserResponseDto {
	public int userId;
	public String fullName;
	public String emailId;
	public Long mobileNo;
}
