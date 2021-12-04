package com.team5.HAPark.Food.DAO;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FoodPersistenceFactoryTest {

    private IFoodPersistence foodPersistence1;
    private IFoodPersistence foodPersistence2;

    @BeforeEach
    void setUp() {
        FoodPersistenceFactory foodPersistenceFactory1 = new FoodPersistenceFactory();
        foodPersistence1 = foodPersistenceFactory1.createFoodPersistence();

        FoodPersistenceFactory foodPersistenceFactory2 = new FoodPersistenceFactory();
        foodPersistence2 = foodPersistenceFactory2.createFoodPersistence();
    }

    @Test
    void createFoodPersistenceSameInstance() {
        assertEquals(foodPersistence1,foodPersistence2);
    }

    @Test
    void createFoodPersistenceNotNull() {
        assertNotNull(foodPersistence1);
    }
}