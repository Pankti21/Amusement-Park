package com.team5.HAPark.Food;

import com.team5.HAPark.Food.DAO.IFoodPersistence;

import java.sql.SQLException;

public class FoodService implements IFoodService {

    private final IFoodPersistence foodPersistence;

    public FoodService(IFoodPersistence foodPersistence){
        this.foodPersistence = foodPersistence;
    }

    @Override
    public Food getFood(String id) throws SQLException {

        Food food = foodPersistence.loadFood(id);

        return food;
    }

    @Override
    public Menu getMenu() throws SQLException {

        Menu menu = foodPersistence.loadMenu();

        return menu;
    }
}
