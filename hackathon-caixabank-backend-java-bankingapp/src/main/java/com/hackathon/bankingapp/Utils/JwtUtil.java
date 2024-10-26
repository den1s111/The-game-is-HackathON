package com.hackathon.bankingapp.Utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil 
{

    private final String SECRET_KEY = "your_secret_key";

    public String generateToken(String subject) 
    {
        return Jwts.builder()
                   .setSubject(subject)
                   .setIssuedAt(new Date(System.currentTimeMillis()))
                   .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))  // 1 hour expiration
                   .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                   .compact();
    }

    public boolean validateToken(String token, String subject) 
    {
        final String username = extractUsername(token);
        return (username.equals(subject) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) 
    {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody().getExpiration().before(new Date());
    }

    public String extractUsername(String token) 
        {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody().getSubject();
    }
}
