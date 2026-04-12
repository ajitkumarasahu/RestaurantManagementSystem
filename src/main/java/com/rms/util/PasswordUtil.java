package com.rms.util;

// Import BCrypt library for password hashing
import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtil {

    // =========================
    // HASH PASSWORD (REGISTER)
    // =========================
    public static String hashPassword(String password) {

        // BCrypt.gensalt(10)
        // → generates a salt with strength (cost factor) 10
        // → higher value = more secure but slower

        // hashpw() → hashes the plain password with generated salt
        return BCrypt.hashpw(password, BCrypt.gensalt(10));
    }

    // =========================
    // CHECK PASSWORD (LOGIN)
    // =========================
    public static boolean checkPassword(String plainPassword, String hashedPassword) {

        // checkpw() compares:
        // - plain text password entered by user
        // - hashed password stored in database

        // Returns:
        // true  → password matches
        // false → password does NOT match
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }
}