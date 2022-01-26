package com.bridgelabz.bookstore.utilis;

import java.util.Base64;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {

	String key = "Secret-key";
	
	public String generateToken(String id, String subject) {
	
		return Jwts.builder()
		.setId(id)
		.setSubject(subject)
		.setIssuer("Amit")
		.setIssuedAt(new Date(System.currentTimeMillis()))
		.signWith(SignatureAlgorithm.HS256, Base64.getEncoder().encode(key.getBytes()))
		.compact();
		
		//.setExpiration(new Date(System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(10)))
	}
	
	//Generating token when user login and this will expires in 24 Hours.
	public String generateTokenForLogin(String email) {
		return Jwts.builder()
				.setSubject(email)
				.setIssuer("Amit")
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.signWith(SignatureAlgorithm.HS256, Base64.getEncoder().encode(key.getBytes()))
				.setExpiration(new Date( System.currentTimeMillis() + TimeUnit.HOURS.toMillis(24)))
				.compact();
	}
	
	public Claims getClaims(String token, String key) {
		return Jwts.parser()
		.setSigningKey(Base64.getEncoder().encode(key.getBytes()))
		.parseClaimsJws(token)
		.getBody();
	}
	
	public String getEmailFromToken(String token) {
		return getClaims(token, key).getSubject();
	}

	public boolean isValidToken(String token) {
		return getClaims(token, key).getExpiration().after(new Date(System.currentTimeMillis()));
	}
}
