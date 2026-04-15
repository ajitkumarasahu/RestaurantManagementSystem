package com.rms.dao;

import com.rms.model.User;
import com.rms.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    // =========================
    // REGISTER USER (INSERT)
    // =========================
    public boolean registerUser(User user) {
        try {
            // Get database connection
            Connection con = DBConnection.getConnection();

            // SQL query to insert user data into 'users' table
            String sql = "INSERT INTO users(name,email,password,role) VALUES(?,?,?,?)";

            // PreparedStatement helps prevent SQL Injection
            PreparedStatement ps = con.prepareStatement(sql);

            // Set values from User object
            ps.setString(1, user.getName());      // name
            ps.setString(2, user.getEmail());     // email
            ps.setString(3, user.getPassword());  // password (should be hashed)
            ps.setString(4, user.getRole());      // role (e.g., ADMIN, USER)

            // Execute query (returns number of rows affected)
            int rows = ps.executeUpdate();

            // Debug log
            System.out.println("Rows inserted: " + rows);

            // Return true if at least 1 row inserted
            return rows > 0;

        } catch (Exception e) {
            // Print error details
            e.printStackTrace();
            System.out.println("❌ REGISTER ERROR");

            // Return false if any error occurs
            return false;
        }
    }

    // =========================
    // GET USER BY EMAIL (LOGIN)
    // =========================
    public User getUserByEmail(String email) {
        try {
            // Get database connection
            Connection con = DBConnection.getConnection();

            // SQL query to find user by email
            String sql = "SELECT * FROM users WHERE email=?";

            PreparedStatement ps = con.prepareStatement(sql);

            // Set email parameter
            ps.setString(1, email);

            // Execute query
            ResultSet rs = ps.executeQuery();

            // If user found
            if (rs.next()) {

                // Create User object and map DB values
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password")); // hashed password
                user.setRole(rs.getString("role"));

                return user;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Return null if user not found
        return null;
    }

}