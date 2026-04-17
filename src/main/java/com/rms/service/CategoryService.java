package com.rms.service;

import com.rms.dao.CategoryDAO;
import com.rms.model.Category;
import java.util.*;

public class CategoryService {
    
    CategoryDAO dao;

    public CategoryService() throws Exception {
        dao = new CategoryDAO();
    }

    public boolean create(Category c) throws Exception {
        return dao.addCategory(c);
    }

    public List<Category> getByRestaurant(int id) throws Exception {
        return dao.getByRestaurant(id);
    }

    public boolean update(Category c) throws Exception {
        return dao.updateCategory(c);
    }

    public boolean delete(int id) throws Exception {
        return dao.deleteCategory(id);
    }
}