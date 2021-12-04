package com.team5.HAPark.Food;

import com.team5.HAPark.Food.DAO.FoodPersistenceFactory;
import com.team5.HAPark.Database.mysql.MySQLDatabase;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
public class FoodController {

    @RequestMapping(value = "/menu")
    public Menu getAllFoods() throws SQLException {

        FoodService foodService = new FoodService(new FoodPersistenceFactory().createFoodPersistence());

        Menu menu = foodService.getMenu();
        MySQLDatabase.getInstance().close();

        return menu;
    }
}

