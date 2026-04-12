package com.rms.util;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtil {

    // Hash password before storing in DB
    public static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(10));
    }

    // Check login password
    public static boolean checkPassword(String plainPassword, String hashedPassword) {
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }
}