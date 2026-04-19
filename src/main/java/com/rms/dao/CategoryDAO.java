package com.rms.dao;

// Import model and DB connection
import com.rms.model.Category;
import com.rms.util.DBConnection;

import java.sql.*;
import java.util.*;

public class CategoryDAO {

    // Database connection object
    Connection conn;

    // Constructor → initialize DB connection
    public CategoryDAO() throws Exception {
        conn = DBConnection.getConnection();
    }

    // ================= CREATE CATEGORY =================
    public boolean addCategory(Category category) throws Exception {

        // SQL insert query
        String sql = "INSERT INTO categories(name,restaurant_id) VALUES(?,?)";

        // Prepare statement
        PreparedStatement ps = conn.prepareStatement(sql);

        // Set values
        ps.setString(1, category.getName());
        ps.setInt(2, category.getRestaurantId());

        // Execute query → returns number of rows affected
        return ps.executeUpdate() > 0;
    }

    // ================= GET BY RESTAURANT =================
    public List<Category> getByRestaurant(int restaurantId) throws Exception {

        List<Category> list = new ArrayList<>();

        String sql = "SELECT * FROM categories WHERE restaurant_id=?";

        PreparedStatement ps = conn.prepareStatement(sql);

        // Set restaurant ID
        ps.setInt(1, restaurantId);

        ResultSet rs = ps.executeQuery();

        // Loop through result set
        while (rs.next()) {

            // Convert DB row → Category object
            list.add(new Category(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getInt("restaurant_id")
            ));
        }

        return list;
    }

    // ================= UPDATE CATEGORY =================
    public boolean updateCategory(Category c) throws Exception {

        String sql = "UPDATE categories SET name=? WHERE id=?";

        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setString(1, c.getName());
        ps.setInt(2, c.getId());

        return ps.executeUpdate() > 0;
    }

    // ================= DELETE CATEGORY =================
    public boolean deleteCategory(int id) throws Exception {

        String sql = "DELETE FROM categories WHERE id=?";

        PreparedStatement ps = conn.prepareStatement(sql);

        // 🔥 BUG FIX: missing parameter
        ps.setInt(1, id);

        return ps.executeUpdate() > 0;
    }
}