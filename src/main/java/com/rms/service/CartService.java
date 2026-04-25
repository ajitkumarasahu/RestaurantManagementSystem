package com.rms.service;

import com.rms.dao.CartDAO;
import com.rms.model.CartItem;
import java.util.List;

public class CartService {

    CartDAO dao = new CartDAO();

    public int getOrCreateCart(int userId) throws Exception {
        return dao.getOrCreateCart(userId);
    }

    public void addItem(CartItem item) throws Exception {
        dao.addItem(item);
    }

    public List<CartItem> getItems(int cartId) throws Exception {
        return dao.getCartItems(cartId);
    }

    public void updateQty(int id,int qty) throws Exception {
        dao.updateQuantity(id,qty);
    }

    public void deleteItem(int id) throws Exception {
        dao.deleteItem(id);
    }

    public void clearCart(int cartId) throws Exception {
        dao.clearCart(cartId);
    }
}