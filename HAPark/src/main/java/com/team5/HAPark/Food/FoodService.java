package com.team5.HAPark.Food;

import com.team5.HAPark.Food.DAO.IFoodPersistence;

import java.sql.SQLException;

public class FoodService {

    private IFoodPersistence foodPersistence;

    public FoodService(IFoodPersistence foodPersistence){
        this.foodPersistence = foodPersistence;
    }

    public Food getFood(String id) throws SQLException {

        Food food = null;
        food = foodPersistence.getFood(id);

        return food;
    }

    public Menu getMenu() throws SQLException {

        Menu menu = null;

        menu = foodPersistence.getMenu();

        return menu;
    }
}
