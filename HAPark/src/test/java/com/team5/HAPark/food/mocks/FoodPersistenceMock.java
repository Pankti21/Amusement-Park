package com.team5.HAPark.food.mocks;

import com.team5.HAPark.food.DAO.IFoodPersistence;
import com.team5.HAPark.food.Food;
import com.team5.HAPark.food.Menu;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class FoodPersistenceMock implements IFoodPersistence {

    private HashMap<String, Food> foods;

    public FoodPersistenceMock() {
        this.foods = new HashMap<>();
        Food pizza = new Food("pizza","1",5);
        foods.put("1",pizza);
    }

    @Override
    public Food loadFood(String id) throws SQLException {
        return foods.get(id);
    }

    @Override
    public Menu loadMenu() throws SQLException {
        Menu menu = new Menu();
        for (Map.Entry<String,Food> foodEntry : foods.entrySet()) {
            menu.addFoodToMenu(foodEntry.getValue());
        }
        return menu;
    }
}
