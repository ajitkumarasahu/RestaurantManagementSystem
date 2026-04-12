package com.rms.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Date;

public class JwtUtil {

    // Must be 32+ characters for HS256
    private static final String SECRET_KEY = "restaurant_secret_key_12345678901234567890";

            
    private static final long EXPIRATION_TIME = 86400000; // 1 day

    // Generate signing key
    private static SecretKey getSigningKey() {
        byte[] keyBytes = SECRET_KEY.getBytes();
        return Keys.hmacShaKeyFor(keyBytes);
    }

    // 🔐 Generate Token
    public static String generateToken(String email, String role) {
        return Jwts.builder()
            .subject(email)
            .claim("role", role)
            .issuedAt(new Date())
            .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
            .signWith(getSigningKey())
            .compact();
    }

    // Extract email
    public static String extractEmail(String token) {
        return getClaims(token).getSubject();
    }

    // Extract role
    public static String extractRole(String token) {
        return (String) getClaims(token).get("role");
    }

    // Validate token
    public static boolean validateToken(String token) {
        try {
            getClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private static Claims getClaims(String token) {
        return Jwts.parser()
            .verifyWith(getSigningKey())
            .build()
            .parseSignedClaims(token)
            .getPayload();
    }
}