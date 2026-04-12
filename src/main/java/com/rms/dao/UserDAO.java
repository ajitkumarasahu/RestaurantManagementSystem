package com.rms.dao;

import com.rms.model.User;
import com.rms.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {

    // REGISTER USER
    public boolean registerUser(User user) {
        try {
            Connection con = DBConnection.getConnection();

            String sql = "INSERT INTO users(name,email,password,role) VALUES(?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getRole());

            int rows = ps.executeUpdate();
            System.out.println("Rows inserted: " + rows);
            return rows > 0;

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("❌ REGISTER ERROR");
            return false;
        }
    }

    // FIND USER BY EMAIL (for login)
    public User getUserByEmail(String email) {
        try {
            Connection con = DBConnection.getConnection();

            String sql = "SELECT * FROM users WHERE email=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, email);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getString("role"));
                return user;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}