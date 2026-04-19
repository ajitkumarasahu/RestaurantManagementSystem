package com.rms.service;

// Import DAO and model
import com.rms.dao.FoodItemDAO;
import com.rms.model.FoodItem;

import java.util.*;

public class FoodService {

    // DAO object (handles DB operations)
    FoodItemDAO dao;

    // Constructor → initialize DAO
    public FoodService() throws Exception {
        dao = new FoodItemDAO();
    }

    // ================= CREATE FOOD =================
    public boolean create(FoodItem f) throws Exception {

        // Calls DAO to insert food into DB
        return dao.addFood(f);
    }

    // ================= GET FOOD BY RESTAURANT =================
    public List<FoodItem> getByRestaurant(int id) throws Exception {

        // Fetch all food items of a restaurant
        return dao.getByRestaurant(id);
    }

    // ================= UPDATE FOOD =================
    public boolean update(FoodItem f) throws Exception {

        // Update food details
        return dao.updateFood(f);
    }

    // ================= DELETE FOOD =================
    public boolean delete(int id) throws Exception {

        // Delete food by ID
        return dao.deleteFood(id);
    }
}