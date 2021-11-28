package com.team5.HAPark.Food;

import com.team5.HAPark.Food.DAO.MySQLFoodPersistence;
import com.team5.HAPark.database.mysql.MySQLDatabase;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
public class FoodController {

    @RequestMapping(value = "/menu")
    public Menu getAllFoods() throws SQLException {

        MySQLDatabase dataBase = MySQLDatabase.getInstance();
        FoodService foodService = new FoodService(new MySQLFoodPersistence(dataBase));

        Menu menu = foodService.getMenu();
        dataBase.close();

        return menu;
    }

    @RequestMapping(value = "/menu/{id}")
    public Food getFood(@PathVariable String id) throws SQLException {

        MySQLDatabase dataBase = MySQLDatabase.getInstance();
        FoodService foodService = new FoodService(new MySQLFoodPersistence(dataBase));

        Food food = foodService.getFood(id);
        dataBase.close();

        return food;
    }
}

