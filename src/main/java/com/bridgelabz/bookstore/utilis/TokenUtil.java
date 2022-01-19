package com.bridgelabz.bookstore.utilis;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.auth0.jwt.interfaces.Verification;
import org.springframework.stereotype.Component;

@Component
public class TokenUtil {
	public static  final String TOKEN_SECRET = "Bridgelabz";

	public static String createToken(Long id) {
		try {
			Algorithm alogirthm = Algorithm.HMAC256(TOKEN_SECRET);

			String token = JWT.create().withClaim("user_id", id).sign(alogirthm);
			return token;
		} catch (JWTCreationException exception) {
			exception.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static int decodeToken(String token) {
		int userid;
		Verification verification = null;

		try {
			verification = JWT.require(Algorithm.HMAC256(TOKEN_SECRET));
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		JWTVerifier jwtVerifier = verification.build();

		DecodedJWT decodedJWT = jwtVerifier.verify(token);
		Claim claim = decodedJWT.getClaim("user_id");
		userid = claim.asInt();
		return userid;
	}

}

