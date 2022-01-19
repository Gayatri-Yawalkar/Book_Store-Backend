package com.bridgelabz.bookstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class MailService {

	@Autowired
	private JavaMailSender javaMailSender;

	public void sendNotification(String toEmail) {
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setTo(toEmail);
		simpleMailMessage.setFrom("gpydevare@gmail.com");
		simpleMailMessage.setSubject("Reset Password");
		simpleMailMessage.setText("To reset your Password, please click here : "
	            +"http://localhost:8080/resetPassword/");
		javaMailSender.send(simpleMailMessage);
	}
}
