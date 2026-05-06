package com.rms.dao;

import com.rms.model.Order;
import com.rms.util.DBConnection;
import java.sql.*;
import java.util.*;

public class OrderDAO {

    // CREATE ORDER
    public int createOrder(Order order) throws Exception {
        Connection con = DBConnection.getConnection();
        String sql = "INSERT INTO orders(user_id, restaurant_id, total_amount, status) VALUES(?,?,?,?)";

        PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, order.getUserId());
            ps.setInt(2, order.getRestaurantId());
            ps.setDouble(3, order.getTotalAmount());
            ps.setString(4, order.getStatus());

            ps.executeUpdate();
        ResultSet rs = ps.getGeneratedKeys();
        if (rs.next()) return rs.getInt(1);
        return 0;
    }

    // GET ALL ORDERS
    public List<Order> getAllOrders() throws Exception {
        Connection con = DBConnection.getConnection();
        List<Order> list = new ArrayList<>();
        String sql = "SELECT * FROM orders";

        ResultSet rs = con.prepareStatement(sql).executeQuery();
        while (rs.next()) {
            Order o = new Order();
            o.setId(rs.getInt("id"));
            o.setUserId(rs.getInt("user_id"));
            o.setRestaurantId(rs.getInt("restaurant_id"));
            o.setTotalAmount(rs.getDouble("total_amount"));
            o.setStatus(rs.getString("status"));
            list.add(o);
        }
        return list;
    }

    // GET ORDER BY ID
    public Order getOrderById(int id) throws Exception {
        Connection con = DBConnection.getConnection();
        String sql = "SELECT * FROM orders WHERE id=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, id);

        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            Order o = new Order();
            o.setId(rs.getInt("id"));
            o.setUserId(rs.getInt("user_id"));
            o.setRestaurantId(rs.getInt("restaurant_id"));
            o.setTotalAmount(rs.getDouble("total_amount"));
            o.setStatus(rs.getString("status"));
            return o;
        }
        return null;
    }

    // UPDATE STATUS
    public boolean updateStatus(int id, String status) throws Exception {
        Connection con = DBConnection.getConnection();
        String sql = "UPDATE orders SET status=? WHERE id=?";
        PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, status);
            ps.setInt(2, id);
        return ps.executeUpdate() > 0;
    }

    // DELETE ORDER
    public boolean deleteOrder(int id) throws Exception {
        Connection con = DBConnection.getConnection();
        String sql = "DELETE FROM orders WHERE id=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, id);
        return ps.executeUpdate() > 0;
    }
}