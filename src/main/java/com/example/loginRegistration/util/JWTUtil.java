package com.example.loginRegistration.util;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JWTUtil {
	private final String secret = "aVeryLongSecretKeyForJwtAuthenticationThatIsAtLeast256BitsLong123456\r\n";
	private final SecretKey key = Keys.hmacShaKeyFor(secret.getBytes());
	private final long ExpirationTime = 1000*60*60 ;// 1 hour
	
	public String genretedToken(String username) {
		return Jwts.builder()
				.setSubject(username)
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + ExpirationTime))
				.signWith(key, SignatureAlgorithm.HS256)
				.compact();
				
	}

}
