package com.rms.service; // Service layer (business logic layer)

// Import DAO and model
import com.rms.dao.CartDAO;
import com.rms.model.CartItem;

import java.util.List; // For returning list of cart items

public class CartService {

    // Create DAO object to interact with database
    CartDAO dao = new CartDAO();

    // =====================================
    // GET OR CREATE CART
    // =====================================
    public int getOrCreateCart(int userId) throws Exception {

        // Calls DAO method to fetch or create cart
        return dao.getOrCreateCart(userId);
    }

    // =====================================
    // ADD ITEM TO CART
    // =====================================
    public void addItem(CartItem item) throws Exception {

        // Pass item to DAO to insert into DB
        dao.addItem(item);
    }

    // =====================================
    // GET ALL ITEMS IN CART
    // =====================================
    public List<CartItem> getItems(int cartId) throws Exception {

        // Fetch list of cart items from DAO
        return dao.getCartItems(cartId);
    }

    // =====================================
    // UPDATE ITEM QUANTITY
    // =====================================
    public void updateQty(int id,int qty) throws Exception {

        // Update quantity using DAO
        dao.updateQuantity(id,qty);
    }

    // =====================================
    // DELETE SINGLE ITEM
    // =====================================
    public void deleteItem(int id) throws Exception {

        // Remove item from DB via DAO
        dao.deleteItem(id);
    }

    // =====================================
    // CLEAR ENTIRE CART
    // =====================================
    public void clearCart(int cartId) throws Exception {

        // Delete all items for given cart
        dao.clearCart(cartId);
    }
}