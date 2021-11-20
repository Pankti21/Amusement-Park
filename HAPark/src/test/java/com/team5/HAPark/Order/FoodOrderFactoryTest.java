package com.team5.HAPark.Order;

import com.team5.HAPark.Order.DAO.IOrderPersistence;
import database.mysql.MySQLDatabase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class FoodOrderFactoryTest {

    private FoodOrderFactory foodOrderFactory;
    private MySQLDatabase database;

    @BeforeEach
    void setUp() {
        database = Mockito.mock(MySQLDatabase.class);
        foodOrderFactory = new FoodOrderFactory();
    }

    @Test
    void createOrderPersistence() throws SQLException {
        IOrderPersistence orderPersistence = foodOrderFactory.createOrderPersistence(database);
        assertNotNull(orderPersistence);
    }
}