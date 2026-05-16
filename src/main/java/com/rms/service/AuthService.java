package com.rms.service;

// Import DAO (Database Access Object)
import com.rms.dao.UserDAO;

// Import User model
import com.rms.model.User;

// Import JWT utility for token generation
import com.rms.security.JwtUtil;

// Utility class for password hashing & verification
import com.rms.util.PasswordUtil;

public class AuthService {

    // DAO object to interact with database
    UserDAO userDAO = new UserDAO();

    // =========================
    // REGISTER METHOD
    // =========================
    public String register(User user) {

        // Check if role is provided
        if (user.getRole() == null)
            return "Role is required";

        // Convert role to uppercase (standardization)
        user.setRole(user.getRole().toUpperCase());

        // Validate allowed roles
        if (!user.getRole().equals("ADMIN") &&
            !user.getRole().equals("CUSTOMER") &&
            !user.getRole().equals("OWNER")) {

            return "Invalid role. Use CUSTOMER / OWNER / ADMIN";
        }

        // Hash password before saving to database (security best practice)
        String hashed = PasswordUtil.hashPassword(user.getPassword());
        user.setPassword(hashed);

        // Save user in database via DAO
        boolean saved = userDAO.registerUser(user);

        // Return result message
        if (saved) {
            return "Registered Successfully";
        } else {
            return "Registration Failed";
        }
    }

    // =========================
    // LOGIN METHOD
    // =========================
    public User login(String email, String password) {

        // Fetch user from DB using email
        User dbUser = userDAO.getUserByEmail(email);

        // Check:
        // 1. User exists
        // 2. Password matches (hashed comparison)
        if (dbUser != null &&
            PasswordUtil.checkPassword(password, dbUser.getPassword())) {

            // Return user object if valid
            return dbUser;
        }

        // Return null if invalid login
        return null;
    }
}