package com.example.eventura.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    private final Key signingKey;
    private final String issuer;

    public JwtUtil(@Value("${eventura.security.jwt.secret}") String secret,
                   @Value("${eventura.security.jwt.issuer:eventura}") String issuer) {
        // secret must be long enough for HS256
        this.signingKey = Keys.hmacShaKeyFor(secret.getBytes());
        this.issuer = issuer;
    }

    public Jws<Claims> validateToken(String token) throws JwtException {
        // Parses and validates signature + expiry
        Jws<Claims> claimsJws = Jwts.parserBuilder()
                .setSigningKey(signingKey)
                .requireIssuer(issuer)
                .build()
                .parseClaimsJws(token);
        // Additional checks (roles, subject) can be added here
        return claimsJws;
    }

    public boolean isTokenExpired(Claims claims) {
        Date exp = claims.getExpiration();
        return exp != null && exp.before(new Date());
    }

    // Optional helper to extract subject
    public String getSubject(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(signingKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
