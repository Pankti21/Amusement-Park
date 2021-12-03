package com.team5.HAPark.food.DAO;

import com.team5.HAPark.food.Food;
import com.team5.HAPark.food.Menu;

import java.sql.SQLException;

public interface IFoodPersistence {
    Food loadFood(String id) throws SQLException;
    Menu loadMenu() throws SQLException;
}
