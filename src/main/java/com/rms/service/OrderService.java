package com.rms.service;

import com.rms.dao.OrderDAO;
import com.rms.dao.OrderItemDAO;
import com.rms.model.*;
import java.util.List;

public class OrderService {

    private OrderDAO orderDAO = new OrderDAO();
    private OrderItemDAO itemDAO = new OrderItemDAO();

    public int placeOrder(Order order, List<OrderItem> items) throws Exception {
        int orderId = orderDAO.createOrder(order);
        for (OrderItem item : items) {
            item.setOrderId(orderId);
            itemDAO.addOrderItem(item);
        }
        return orderId;
    }

    public List<Order> getAllOrders() throws Exception {
        return orderDAO.getAllOrders();
    }

    public Order getOrderById(int id) throws Exception {
        return orderDAO.getOrderById(id);
    }

    public boolean updateStatus(int id, String status) throws Exception {
        return orderDAO.updateStatus(id, status);
    }

    public boolean deleteOrder(int id) throws Exception {
        return orderDAO.deleteOrder(id);
    }
}