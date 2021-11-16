package com.team5.HAPark.Food.DAO;

import com.team5.HAPark.Food.Food;
import com.team5.HAPark.Food.Menu;

import java.sql.SQLException;

public interface IFoodPersistence {
    Food loadFood(String id) throws SQLException;
    Menu loadMenu() throws SQLException;
}
