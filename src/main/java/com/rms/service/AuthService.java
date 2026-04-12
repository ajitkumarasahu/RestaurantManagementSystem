package com.rms.service;

import com.rms.dao.UserDAO;
import com.rms.model.User;
import com.rms.util.PasswordUtil;

public class AuthService {

    UserDAO userDAO = new UserDAO();

    // REGISTER
    public String register(User user) {

        // normalize role to uppercase
        if (user.getRole() == null)
            return "Role is required";

        user.setRole(user.getRole().toUpperCase());

        // validate role values
        if (!user.getRole().equals("ADMIN") && !user.getRole().equals("CUSTOMER") && !user.getRole().equals("OWNER")) {
            
            return "Invalid role. Use CUSTOMER / OWNER / ADMIN";
        }

        // hash password before saving
        String hashed = PasswordUtil.hashPassword(user.getPassword());
        user.setPassword(hashed);

        boolean saved = userDAO.registerUser(user);

        if (saved){
            return "User Registered Successfully";   
        } else {
            return "Registration Failed";
        }
    }

    // LOGIN
    public User login(String email, String password) {

        User dbUser = userDAO.getUserByEmail(email);

        if (dbUser != null && PasswordUtil.checkPassword(password, dbUser.getPassword())) {
                
            return dbUser;
        }

        return null;
    }
}