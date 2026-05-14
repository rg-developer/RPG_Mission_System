package com.prueba1.main.rest.Security;

import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {

	private final String SECRET = "158f2f4e666a2fa296cfa9d91aea784992fceff551aae9814833f143c1470537";
	private final long EXPIRATION = 86400000;
	
	public String generateToken(String username) {
	    return Jwts.builder()
	            .setSubject(username)
	            .setIssuedAt(new Date(System.currentTimeMillis()))
	            .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
	            .signWith(SignatureAlgorithm.HS256, SECRET)
	            .compact();
	}
	
	public String getUsername(String token) {
		return getClaims(token).getSubject();
	}
	
	public Claims getClaims (String token) {
		return Jwts.parser()
				.setSigningKey(SECRET)
				.parseClaimsJws(token)
				.getBody();
	}
	
	public boolean validToken (String token) {
		try {
			getClaims(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
