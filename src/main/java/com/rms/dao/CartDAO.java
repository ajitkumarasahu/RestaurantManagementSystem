package com.rms.dao;

import com.rms.model.Cart;
import com.rms.model.CartItem;
import com.rms.util.DBConnection;

import java.sql.*;
import java.util.*;

public class CartDAO {

    // Create cart if not exists
    public int getOrCreateCart(int userId) throws Exception {
        Connection con = DBConnection.getConnection();

        PreparedStatement check = con.prepareStatement("SELECT id FROM carts WHERE user_id=?");
        check.setInt(1,userId);
        ResultSet rs = check.executeQuery();

        if(rs.next()) return rs.getInt("id");

        PreparedStatement create = con.prepareStatement(
            "INSERT INTO carts(user_id) VALUES(?)", Statement.RETURN_GENERATED_KEYS);
        create.setInt(1,userId);
        create.executeUpdate();

        ResultSet gen = create.getGeneratedKeys();
        gen.next();
        return gen.getInt(1);
    }

    // Add item
    public void addItem(CartItem item) throws Exception {
        Connection con = DBConnection.getConnection();

        PreparedStatement ps = con.prepareStatement(
            "INSERT INTO cart_items(cart_id,food_item_id,quantity) VALUES(?,?,?)");

        ps.setInt(1,item.getCartId());
        ps.setInt(2,item.getFoodItemId());
        ps.setInt(3,item.getQuantity());
        ps.executeUpdate();
    }

    // Get cart items
    public List<CartItem> getCartItems(int cartId) throws Exception {
        List<CartItem> list = new ArrayList<>();
        Connection con = DBConnection.getConnection();

        PreparedStatement ps = con.prepareStatement("SELECT * FROM cart_items WHERE cart_id=?");
        ps.setInt(1,cartId);
        ResultSet rs = ps.executeQuery();

        while(rs.next()){
            list.add(new CartItem(
                rs.getInt("id"),
                rs.getInt("cart_id"),
                rs.getInt("food_item_id"),
                rs.getInt("quantity")
            ));
        }
        return list;
    }

    // Update quantity
    public void updateQuantity(int id,int qty) throws Exception {
        Connection con = DBConnection.getConnection();
        PreparedStatement ps = con.prepareStatement(
            "UPDATE cart_items SET quantity=? WHERE id=?");
        ps.setInt(1,qty);
        ps.setInt(2,id);
        ps.executeUpdate();
    }

    // Delete item
    public void deleteItem(int id) throws Exception {
        Connection con = DBConnection.getConnection();
        PreparedStatement ps = con.prepareStatement("DELETE FROM cart_items WHERE id=?");
        ps.setInt(1,id);
        ps.executeUpdate();
    }

    // Clear cart
    public void clearCart(int cartId) throws Exception {
        Connection con = DBConnection.getConnection();
        PreparedStatement ps = con.prepareStatement("DELETE FROM cart_items WHERE cart_id=?");
        ps.setInt(1,cartId);
        ps.executeUpdate();
    }
}