package com.example.demo.jwt;

import java.util.Base64;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTUtil {

	private static final String AUTHORITIES_KEY = "AUTHORIZATION";
	
	@Value("${app.secret-key}")
	private String secretKey;
	
	public String generateToken(Authentication authentication)
	{
		final String authorities = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(","));;
		
		return Jwts.builder().setId("AUTH SERVER")
					  .setSubject(authentication.getName())
					  .setIssuer("AUTHORIZATION")
					  .claim(AUTHORITIES_KEY, authorities)
					  .setIssuedAt(new Date(System.currentTimeMillis()))
					  .setExpiration(new Date(System.currentTimeMillis()+TimeUnit.MINUTES.toMillis(30)))
					  .signWith(SignatureAlgorithm.HS256,Base64.getEncoder().encode(secretKey.getBytes()))
					  .compact();
	}
	
}
