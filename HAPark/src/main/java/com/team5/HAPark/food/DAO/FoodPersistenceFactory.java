package com.team5.HAPark.food.DAO;

import com.team5.HAPark.database.mysql.MySQLDatabase;

public class FoodPersistenceFactory implements IFoodPersistenceFactory {

    private static IFoodPersistence foodPersistence;

    @Override
    public IFoodPersistence createFoodPersistence() {
        if (foodPersistence == null) {
            foodPersistence = new MySQLFoodPersistence(MySQLDatabase.getInstance());
        }
        return foodPersistence;
    }
}
