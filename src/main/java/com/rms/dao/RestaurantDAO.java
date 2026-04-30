package com.rms.dao;

// Import model and DB connection utility
import com.rms.model.Restaurant;
import com.rms.util.DBConnection;

import java.sql.*;
import java.util.*;

public class RestaurantDAO {

    // =========================
    // CREATE SINGLE RESTAURANT
    // =========================
    public boolean createRestaurant(Restaurant r) {
        try {
            // Get DB connection
            Connection con = DBConnection.getConnection();

            // SQL query with placeholders (?)
            String sql = "INSERT INTO restaurants(name,address,phone,owner_id) VALUES(?,?,?,?)";

            // Prepare statement (prevents SQL injection)
            PreparedStatement ps = con.prepareStatement(sql);

            // Set values
            ps.setString(1, r.getName());
            ps.setString(2, r.getAddress());
            ps.setString(3, r.getPhone());
            ps.setInt(4, r.getOwnerId());

            // Execute query → returns number of rows affected
            return ps.executeUpdate() > 0;

        } catch (Exception e) { 
            e.printStackTrace(); 
        }
        return false;
    }

    // =========================
    // CREATE MULTIPLE (BATCH)
    // =========================
    public boolean createRestaurant(List<Restaurant> restaurants) {
        try {
            Connection con = DBConnection.getConnection();
            String sql = "INSERT INTO restaurants(name,address,phone,owner_id) VALUES(?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);

            // Loop through all restaurants
            for (Restaurant r : restaurants) {

                ps.setString(1, r.getName());
                ps.setString(2, r.getAddress());
                ps.setString(3, r.getPhone());
                ps.setInt(4, r.getOwnerId());

                ps.addBatch(); // Add to batch (not executed yet)
            }

            // Execute all queries together (faster)
            int[] results = ps.executeBatch();

            // Check if all inserts were successful
            for (int res : results) {
                if (res <= 0) {
                    return false;
                }
            }

            return true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // =========================
    // GET ALL RESTAURANTS
    // =========================
    public List<Restaurant> getAllRestaurants() {

        List<Restaurant> list = new ArrayList<>();

        try {
            Connection con = DBConnection.getConnection();

            // Execute SELECT query
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM restaurants");

            // Loop through result set
            while (rs.next()) {

                // Create object from DB row
                Restaurant r = new Restaurant(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("address"),
                    rs.getString("phone"),
                    rs.getInt("owner_id"),
                    rs.getString("created_at")
                );

                list.add(r); // Add to list
            }

        } catch (Exception e) { 
            e.printStackTrace(); 
        }

        return list;
    }

    // =========================
    // GET BY ID
    // =========================
    public Restaurant getRestaurantById(int id) {
        try {
            Connection con = DBConnection.getConnection();

            // Use PreparedStatement for safety
            PreparedStatement ps = con.prepareStatement(
                "SELECT * FROM restaurants WHERE id=?"
            );

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Restaurant(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("address"),
                    rs.getString("phone"),
                    rs.getInt("owner_id"),
                    rs.getString("created_at")
                );
            }

        } catch (Exception e) { 
            e.printStackTrace(); 
        }

        return null;
    }

    // =========================
    // UPDATE SINGLE
    // =========================
    public boolean updateRestaurant(Restaurant r) {
        try {
            Connection con = DBConnection.getConnection();

            String sql = "UPDATE restaurants SET name=?, address=?, phone=? WHERE id=?";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, r.getName());
            ps.setString(2, r.getAddress());
            ps.setString(3, r.getPhone());
            ps.setInt(4, r.getId());

            return ps.executeUpdate() > 0;

        } catch (Exception e) { 
            e.printStackTrace(); 
        }

        return false;
    }

    // =========================
    // UPDATE MULTIPLE (BATCH)
    // =========================
    public boolean updateRestaurant(List<Restaurant> restaurants) {
        try {
            Connection con = DBConnection.getConnection();

            String sql = "UPDATE restaurants SET name=?, address=?, phone=? WHERE id=?";
            PreparedStatement ps = con.prepareStatement(sql);

            for (Restaurant r : restaurants) {

                ps.setString(1, r.getName());
                ps.setString(2, r.getAddress()); 
                ps.setInt(4, r.getId());

                ps.addBatch(); // Add to batch
            }

            int[] results = ps.executeBatch();

            for (int res : results) {
                if (res <= 0) return false;
            }

            return true;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // =========================
    // DELETE SINGLE
    // =========================
    public boolean deleteRestaurant(int id) {
        try {
            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(
                "DELETE FROM restaurants WHERE id=?"
            );

            ps.setInt(1, id);

            return ps.executeUpdate() > 0;

        } catch (Exception e) { 
            e.printStackTrace(); 
        }

        return false;
    }

    // =========================
    // DELETE MULTIPLE (BATCH)
    // =========================
    public boolean deleteRestaurant(List<Integer> ids) {
        try {
            Connection con = DBConnection.getConnection();

            String sql = "DELETE FROM restaurants WHERE id=?";
            PreparedStatement ps = con.prepareStatement(sql);

            for (Integer id : ids) {
                ps.setInt(1, id);
                ps.addBatch(); // Add batch
            }

            int[] results = ps.executeBatch();

            for (int res : results) {
                if (res <= 0) return false;
            }

            return true;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}