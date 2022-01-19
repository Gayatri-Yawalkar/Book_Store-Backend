package com.bridgelabz.bookstore.exception;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
@ControllerAdvice
public class BookStoreExceptionHandler {
	@ExceptionHandler(BookStoreException.class)
	public ResponseEntity<String> handleEmployeePayrollException(BookStoreException bookStoreException){
		return new ResponseEntity<String>(bookStoreException.getMessage(),HttpStatus.BAD_REQUEST);
	}
}
