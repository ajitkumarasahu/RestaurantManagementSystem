package com.rms.dao; // DAO layer package (handles database operations)

// Import model classes
import com.rms.model.Cart;
import com.rms.model.CartItem;

// Import DB connection utility
import com.rms.util.DBConnection;

// Import SQL and utility classes
import java.sql.*;
import java.util.*;

public class CartDAO {

    // =====================================
    // GET OR CREATE CART FOR A USER
    // =====================================
    public int getOrCreateCart(int userId) throws Exception {

        // Get database connection
        Connection con = DBConnection.getConnection();

        // Check if cart already exists for the user
        PreparedStatement check = con.prepareStatement(
            "SELECT id FROM carts WHERE user_id=?"
        );

        // Set userId in query
        check.setInt(1,userId);

        // Execute query
        ResultSet rs = check.executeQuery();

        // If cart exists, return cart id
        if(rs.next()) return rs.getInt("id");

        // If not exists → create new cart
        PreparedStatement create = con.prepareStatement(
            "INSERT INTO carts(user_id) VALUES(?)",
            Statement.RETURN_GENERATED_KEYS // get auto-generated id
        );

        // Set userId
        create.setInt(1,userId);

        // Execute insert
        create.executeUpdate();

        // Get generated cart id
        ResultSet gen = create.getGeneratedKeys();
        gen.next();

        // Return new cart id
        return gen.getInt(1);
    }

    // =====================================
    // ADD ITEM TO CART
    // =====================================
    public void addItem(CartItem item) throws Exception {

        // Get DB connection
        Connection con = DBConnection.getConnection();

        // Insert new item into cart_items table
        PreparedStatement ps = con.prepareStatement(
            "INSERT INTO cart_items(cart_id,food_item_id,quantity) VALUES(?,?,?)"
        );

        // Set values from CartItem object
        ps.setInt(1,item.getCartId());      // cart id
        ps.setInt(2,item.getFoodItemId());  // food item id
        ps.setInt(3,item.getQuantity());    // quantity

        // Execute insert
        ps.executeUpdate();
    }

    // =====================================
    // GET ALL ITEMS IN CART
    // =====================================
    public List<CartItem> getCartItems(int cartId) throws Exception {

        // Create list to store cart items
        List<CartItem> list = new ArrayList<>();

        // Get DB connection
        Connection con = DBConnection.getConnection();

        // Query to fetch items for given cart
        PreparedStatement ps = con.prepareStatement(
            "SELECT * FROM cart_items WHERE cart_id=?"
        );

        // Set cartId
        ps.setInt(1,cartId);

        // Execute query
        ResultSet rs = ps.executeQuery();

        // Loop through result set
        while(rs.next()){

            // Create CartItem object and add to list
            list.add(new CartItem(
                rs.getInt("id"),           // item id
                rs.getInt("cart_id"),      // cart id
                rs.getInt("food_item_id"), // food item id
                rs.getInt("quantity")      // quantity
            ));
        }

        // Return list of items
        return list;
    }

    // =====================================
    // UPDATE ITEM QUANTITY
    // =====================================
    public void updateQuantity(int id,int qty) throws Exception {

        // Get DB connection
        Connection con = DBConnection.getConnection();

        // Update quantity of a specific item
        PreparedStatement ps = con.prepareStatement(
            "UPDATE cart_items SET quantity=? WHERE id=?"
        );

        // Set new quantity
        ps.setInt(1,qty);

        // Set item id
        ps.setInt(2,id);

        // Execute update
        ps.executeUpdate();
    }

    // =====================================
    // DELETE SINGLE ITEM FROM CART
    // =====================================
    public void deleteItem(int id) throws Exception {

        // Get DB connection
        Connection con = DBConnection.getConnection();

        // Delete item by id
        PreparedStatement ps = con.prepareStatement(
            "DELETE FROM cart_items WHERE id=?"
        );

        // Set item id
        ps.setInt(1,id);

        // Execute delete
        ps.executeUpdate();
    }

    // =====================================
    // CLEAR ALL ITEMS FROM CART
    // =====================================
    public void clearCart(int cartId) throws Exception {

        // Get DB connection
        Connection con = DBConnection.getConnection();

        // Delete all items belonging to a cart
        PreparedStatement ps = con.prepareStatement(
            "DELETE FROM cart_items WHERE cart_id=?"
        );

        // Set cart id
        ps.setInt(1,cartId);

        // Execute delete
        ps.executeUpdate();
    }
}