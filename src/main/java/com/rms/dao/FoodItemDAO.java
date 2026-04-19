package com.rms.dao;

// Import model and DB connection
import com.rms.model.FoodItem;
import com.rms.util.DBConnection;

import java.sql.*;
import java.util.*;

public class FoodItemDAO {

    // Database connection (shared)
    Connection conn;

    // Constructor → initialize connection
    public FoodItemDAO() throws Exception {
        this.conn = DBConnection.getConnection();
    }

    // ================= ADD FOOD =================
    public boolean addFood(FoodItem food) throws Exception {

        // SQL insert query
        String sql = "INSERT INTO food_items(name,description,price,category_id,restaurant_id) VALUES(?,?,?,?,?)";

        PreparedStatement ps = conn.prepareStatement(sql);

            // Set values
            ps.setString(1, food.getName());
            ps.setString(2, food.getDescription());
            ps.setDouble(3, food.getPrice());
            ps.setInt(4, food.getCategoryId());
            ps.setInt(5, food.getRestaurantId());

        // Execute query
        return ps.executeUpdate() > 0;
    }

    // ================= GET FOOD BY RESTAURANT =================
    public List<FoodItem> getByRestaurant(int restaurantId) throws Exception {

        List<FoodItem> list = new ArrayList<>();

        String sql = "SELECT * FROM food_items WHERE restaurant_id=?";

        PreparedStatement ps = conn.prepareStatement(sql);

        // Set restaurant ID
        ps.setInt(1, restaurantId);

        ResultSet rs = ps.executeQuery();

        // Convert DB rows → Java objects
        while (rs.next()) {
            list.add(new FoodItem(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("description"),
                rs.getDouble("price"),
                rs.getInt("category_id"),
                rs.getInt("restaurant_id")
            ));
        }

        return list;
    }

    // ================= UPDATE FOOD =================
    public boolean updateFood(FoodItem food) throws Exception {

        String sql = "UPDATE food_items SET name=?,description=?,price=? WHERE id=?";

        PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, food.getName());
            ps.setString(2, food.getDescription());
            ps.setDouble(3, food.getPrice());
            ps.setInt(4, food.getId());

        return ps.executeUpdate() > 0;
    }

    // ================= DELETE FOOD =================
    public boolean deleteFood(int id) throws Exception {

        String sql = "DELETE FROM food_items WHERE id=?";

        PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, id);

        return ps.executeUpdate() > 0;
    }
}