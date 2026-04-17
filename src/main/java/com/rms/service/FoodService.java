package com.rms.service;

import com.rms.dao.FoodItemDAO;
import com.rms.model.FoodItem;
import java.util.*;

public class FoodService {

    FoodItemDAO dao;

    public FoodService() throws Exception {
        dao = new FoodItemDAO();
    }

    public boolean create(FoodItem f) throws Exception { 
        return dao.addFood(f); 
    }
    
    public List<FoodItem> getByRestaurant(int id) throws Exception { 
        return dao.getByRestaurant(id); 
    }

    public boolean update(FoodItem f) throws Exception { 
        return dao.updateFood(f); 
    }

    public boolean delete(int id) throws Exception { 
        return dao.deleteFood(id); 
    }
}