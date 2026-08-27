package com.example.loginRegistration.util;

import java.util.Date;

import javax.crypto.SecretKey;
import jakarta.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JWTUtil {
	@Value("${jwt.secret:defaultVeryLongSecretKeyReplaceInProd}")
	private String secret;

	private SecretKey key;
	private final long ExpirationTime = 1000 * 60 * 60; // 1 hour

	@PostConstruct
	public void init() {
		// initialize the signing key after secret is injected
		this.key = Keys.hmacShaKeyFor(secret.getBytes());
	}

	public String genretedToken(String username) {
		return Jwts.builder()
				.setSubject(username)
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + ExpirationTime))
				.signWith(key, SignatureAlgorithm.HS256)
				.compact();

	}

	public String extractUsername(String token) {
		return extractClaims(token).getSubject();
	}

	private Claims extractClaims(String token)	{
		return Jwts.parserBuilder()
				.setSigningKey(key)
				.build()
				.parseClaimsJws(token)
				.getBody();

	}

	public boolean validateToken(String username, UserDetails userDetails, String token) {
		return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
	}

	private boolean isTokenExpired(String token) {
		return extractClaims(token).getExpiration().before(new Date());
	}

}
