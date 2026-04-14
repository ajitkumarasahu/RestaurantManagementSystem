package com.rms.service;

// Import DAO and model
import com.rms.dao.RestaurantDAO;
import com.rms.model.Restaurant;

import java.util.List;

public class RestaurantService {

    // DAO layer object (handles database operations)
    RestaurantDAO dao = new RestaurantDAO();

    // =========================
    // CREATE SINGLE RESTAURANT
    // =========================
    public boolean createRestaurant(Restaurant r) { 
        // Delegates creation to DAO
        return dao.createRestaurant(r); 
    }

    // =========================
    // CREATE MULTIPLE RESTAURANTS
    // =========================
    public boolean createRestaurant(List<Restaurant> restaurants) {
        // Delegates bulk creation to DAO
        return dao.createRestaurant(restaurants);
    }

    // =========================
    // GET ALL RESTAURANTS
    // =========================
    public List<Restaurant> getAllRestaurants() { 
        // Fetch all records from database
        return dao.getAllRestaurants(); 
    }

    // =========================
    // GET RESTAURANT BY ID
    // =========================
    public Restaurant getRestaurantById(int id) { 
        // Fetch single restaurant by ID
        return dao.getRestaurantById(id); 
    }

    // =========================
    // UPDATE SINGLE RESTAURANT
    // =========================
    public boolean updateRestaurant(Restaurant r) { 
        // Update record in database
        return dao.updateRestaurant(r); 
    }

    // =========================
    // UPDATE MULTIPLE RESTAURANTS
    // =========================
    public boolean updateRestaurant(List<Restaurant> restaurants) {
        // Bulk update
        return dao.updateRestaurant(restaurants);
    }

    // =========================
    // DELETE SINGLE RESTAURANT
    // =========================
    public boolean deleteRestaurant(int id) { 
        // Delete by ID
        return dao.deleteRestaurant(id); 
    }

    // =========================
    // DELETE MULTIPLE RESTAURANTS
    // =========================
    public boolean deleteRestaurant(List<Integer> ids) {
        // Bulk delete
        return dao.deleteRestaurant(ids);
    }
}