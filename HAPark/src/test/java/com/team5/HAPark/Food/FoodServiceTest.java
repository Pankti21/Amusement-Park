package com.team5.HAPark.Food;

import com.team5.HAPark.Food.DAO.IFoodPersistence;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class FoodServiceTest {

    static FoodService foodService;
    static IFoodPersistence foodPersistenceMock;
    static Menu menu;
    static Food pizza;

    @BeforeAll
    static void init() throws SQLException {

        pizza = new Food("pizza","1",5);
        menu = new Menu();
        menu.addFoodToMenu(pizza);

        foodPersistenceMock = Mockito.mock(IFoodPersistence.class);
        Mockito.when(foodPersistenceMock.loadMenu()).thenReturn(menu);
        Mockito.when(foodPersistenceMock.loadFood("1")).thenReturn(pizza);
        Mockito.when(foodPersistenceMock.loadFood("2")).thenReturn(null);

        foodService = new FoodService(foodPersistenceMock);
    }

    @Test
    void getFoodExists() throws SQLException {
        assertEquals(pizza,foodService.getFood("1"));
    }

    @Test
    void getFoodNotExists() throws SQLException {
        assertNull(foodService.getFood("2"));
    }

    @Test
    void getMenu() throws SQLException {
        assertEquals(menu, foodService.getMenu());
    }
}