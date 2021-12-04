package com.team5.HAPark.Food;

import java.sql.SQLException;

public interface IFoodService {
    Food getFood(String id) throws SQLException;

    Menu getMenu() throws SQLException;
}
