package com.team5.HAPark.food.mocks;

import com.team5.HAPark.food.DAO.IFoodPersistence;

public class FoodPersistenceMockFactory implements IFoodPersistenceMockFactory {

    @Override
    public IFoodPersistence createFoodPersistenceMock() {
        return new FoodPersistenceMock();
    }
}
