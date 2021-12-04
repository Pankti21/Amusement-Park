package com.team5.HAPark.Food.DAO;

import com.team5.HAPark.Database.mysql.MySQLDatabase;

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
