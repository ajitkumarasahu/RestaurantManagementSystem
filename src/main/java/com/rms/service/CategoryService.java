package com.rms.service;

// Import DAO and model
import com.rms.dao.CategoryDAO;
import com.rms.model.Category;

import java.util.*;

public class CategoryService {
    
    // DAO object (used to access database)
    CategoryDAO dao;

    // Constructor → initialize DAO
    public CategoryService() throws Exception {
        dao = new CategoryDAO();
    }

    // ================= CREATE CATEGORY =================
    public boolean create(Category c) throws Exception {

        // Calls DAO to insert category into DB
        return dao.addCategory(c);
    }

    // ================= GET BY RESTAURANT =================
    public List<Category> getByRestaurant(int id) throws Exception {

        // Fetch categories for a restaurant
        return dao.getByRestaurant(id);
    }

    // ================= UPDATE CATEGORY =================
    public boolean update(Category c) throws Exception {

        // Update category in DB
        return dao.updateCategory(c);
    }

    // ================= DELETE CATEGORY =================
    public boolean delete(int id) throws Exception {

        // Delete category from DB
        return dao.deleteCategory(id);
    }
}