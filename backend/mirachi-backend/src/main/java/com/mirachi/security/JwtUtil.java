package com.mirachi.security;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long expiration;

    /**
     * Creates signing key using secret from application.properties
     */
    private SecretKey getSigningKey() {

        return Keys.hmacShaKeyFor(
                secret.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * Generate JWT Token
     */
    public String generateToken(
            String email,
            String role) {

        return Jwts.builder()

                // User Email
                .subject(email)

                // Custom Claim
                .claim("role", role)

                // Token Creation Time
                .issuedAt(new Date())

                // Token Expiry Time
                .expiration(
                        new Date(
                                System.currentTimeMillis()
                                        + expiration))

                // Signature
                .signWith(getSigningKey())

                .compact();
    }

    /**
     * Extract Email From Token
     */
    public String extractUsername(
            String token) {

        return extractAllClaims(token)
                .getSubject();
    }

    /**
     * Extract Role From Token
     */
    public String extractRole(
            String token) {

        return extractAllClaims(token)
                .get("role", String.class);
    }

    /**
     * Extract Expiration Date
     */
    public Date extractExpiration(
            String token) {

        return extractAllClaims(token)
                .getExpiration();
    }

    /**
     * Common Claims Extraction Method
     */
    private Claims extractAllClaims(
            String token) {

        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    /**
     * Check Token Expiry
     */
    public boolean isTokenExpired(
            String token) {

        return extractExpiration(token)
                .before(new Date());
    }

    /**
     * Validate Token
     */
    public boolean validateToken(
            String token) {

        try {

            return !isTokenExpired(token);

        } catch (Exception e) {

            return false;
        }
    }
}