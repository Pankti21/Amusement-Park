package com.team5.HAPark.Food;

import com.team5.HAPark.Food.DAO.IFoodPersistence;

import java.sql.SQLException;

public class FoodService {

    private final IFoodPersistence foodPersistence;

    public FoodService(IFoodPersistence foodPersistence){
        this.foodPersistence = foodPersistence;
    }

    public Food getFood(String id) throws SQLException {

        Food food = foodPersistence.loadFood(id);

        return food;
    }

    public Menu getMenu() throws SQLException {

        Menu menu = foodPersistence.loadMenu();

        return menu;
    }
}
