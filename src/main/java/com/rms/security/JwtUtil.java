package com.rms.security;

// JWT classes for building and parsing tokens
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Date;

public class JwtUtil {

    // Secret key used to sign JWT
    // Must be at least 32 characters for HS256 algorithm
    private static final String SECRET_KEY = "restaurant_secret_key_12345678901234567890";

    // Token validity time (in milliseconds)
    // 86400000 ms = 1 day
    private static final long EXPIRATION_TIME = 86400000;

    // Generate cryptographic signing key from secret
    private static SecretKey getSigningKey() {
        byte[] keyBytes = SECRET_KEY.getBytes(); // Convert string to bytes
        return Keys.hmacShaKeyFor(keyBytes);     // Create HMAC SHA key
    }

    // =========================
    // 🔐 GENERATE JWT TOKEN
    // =========================
    public static String generateToken(String email, String role) {
        return Jwts.builder()
            .subject(email) // Set email as subject (unique identifier)
            .claim("role", role) // Add custom claim (user role)
            .issuedAt(new Date()) // Token creation time
            .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) // Expiry time
            .signWith(getSigningKey()) // Sign token with secret key
            .compact(); // Build final token string
    }

    // =========================
    // 📧 EXTRACT EMAIL FROM TOKEN
    // =========================
    public static String extractEmail(String token) {
        return getClaims(token).getSubject();
    }

    // =========================
    // 👤 EXTRACT ROLE FROM TOKEN
    // =========================
    public static String extractRole(String token) {
        return (String) getClaims(token).get("role");
    }

    // =========================
    // ✅ VALIDATE TOKEN
    // =========================
    public static boolean validateToken(String token) {
        try {
            // If parsing works, token is valid
            getClaims(token);
            return true;
        } catch (Exception e) {
            // If parsing fails (expired, tampered, invalid)
            return false;
        }
    }

    // =========================
    // 🔍 PARSE TOKEN & GET CLAIMS
    // =========================
    private static Claims getClaims(String token) {
        return Jwts.parser()
            .verifyWith(getSigningKey()) // Verify signature using secret key
            .build()
            .parseSignedClaims(token)   // Parse token
            .getPayload();              // Extract claims (data inside token)
    }
}