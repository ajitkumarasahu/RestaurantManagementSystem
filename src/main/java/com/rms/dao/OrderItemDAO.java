package com.rms.dao;

import com.rms.model.OrderItem;
import com.rms.util.DBConnection;
import java.sql.*;

public class OrderItemDAO {

    public void addOrderItem(OrderItem item) throws Exception {
        Connection con = DBConnection.getConnection();
        String sql = "INSERT INTO order_items(order_id, food_item_id, quantity, price) VALUES(?,?,?,?)";

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, item.getOrderId());
        ps.setInt(2, item.getFoodItemId());
        ps.setInt(3, item.getQuantity());
        ps.setDouble(4, item.getPrice());
        ps.executeUpdate();
    }
}