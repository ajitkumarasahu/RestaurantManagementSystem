package com.rms.dao;

import com.rms.model.Category;
import com.rms.util.DBConnection;

import java.sql.*;
import java.util.*;

public class CategoryDAO {

    Connection conn;

    public CategoryDAO() throws Exception {
        conn = DBConnection.getConnection();
    }

    // CREATE
    public boolean addCategory(Category category) throws Exception {
        String sql = "INSERT INTO categories(name,restaurant_id) VALUES(?,?)";
        PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, category.getName());
            ps.setInt(2, category.getRestaurantId());
        return ps.executeUpdate() > 0;
    }

    // GET BY RESTAURANT
    public List<Category> getByRestaurant(int restaurantId) throws Exception {
        List<Category> list = new ArrayList<>();
        String sql = "SELECT * FROM categories WHERE restaurant_id=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, restaurantId);
        ResultSet rs = ps.executeQuery();

        while(rs.next()){
            list.add(new Category(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getInt("restaurant_id")
            ));
        }
        return list;
    }

    // UPDATE
    public boolean updateCategory(Category c) throws Exception {
        String sql="UPDATE categories SET name=? WHERE id=?";
        PreparedStatement ps=conn.prepareStatement(sql);
            ps.setString(1,c.getName());
            ps.setInt(2,c.getId());
        return ps.executeUpdate()>0;
    }

    // DELETE
    public boolean deleteCategory(int id) throws Exception {
        String sql="DELETE FROM categories WHERE id=?";
        PreparedStatement ps=conn.prepareStatement(sql);
            
        return ps.executeUpdate()>0;
    }
}