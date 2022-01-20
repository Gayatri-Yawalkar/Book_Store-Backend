package com.bridgelabz.bookstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "User Mangement Api", version = "1.0", description = "Api Description"))
public class BooksStoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(BooksStoreApplication.class, args);
	}

}
