package com.rms.dao;

import com.rms.model.Payment;
import com.rms.util.DBConnection;
import java.sql.*;

public class PaymentDAO {

    public void createPayment(Payment p) throws Exception {
        Connection con = DBConnection.getConnection();

        PreparedStatement ps = con.prepareStatement(
        "INSERT INTO payments(order_id,payment_method,payment_status,transaction_id) VALUES(?,?,?,?)");

        ps.setInt(1,p.getOrderId());
        ps.setString(2,p.getPaymentMethod());
        ps.setString(3,p.getPaymentStatus());
        ps.setString(4,p.getTransactionId());

        ps.executeUpdate();
    }

    public Payment getByOrderId(int orderId) throws Exception {
        Connection con = DBConnection.getConnection();

        PreparedStatement ps = con.prepareStatement(
        "SELECT * FROM payments WHERE order_id=?");

        ps.setInt(1,orderId);
        ResultSet rs = ps.executeQuery();

        if(rs.next()){
            Payment p = new Payment();
            p.setId(rs.getInt("id"));
            p.setOrderId(rs.getInt("order_id"));
            p.setPaymentMethod(rs.getString("payment_method"));
            p.setPaymentStatus(rs.getString("payment_status"));
            p.setTransactionId(rs.getString("transaction_id"));
            p.setCreatedAt(rs.getString("created_at"));
            return p;
        }
        return null;
    }
}