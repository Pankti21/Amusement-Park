package com.team5.HAPark.Order;

import com.team5.HAPark.Order.DAO.IOrderPersistence;
import com.team5.HAPark.Order.DAO.MySQLFoodOrderPersistence;
import com.team5.HAPark.Order.model.*;
import com.team5.HAPark.database.mysql.MySQLDatabase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class FoodOrderFactoryTest {

    private FoodOrderFactory foodOrderFactory;
    private MySQLDatabase database;
    private MySQLFoodOrderPersistence mySQLFoodOrderPersistence;

    @BeforeEach
    void setUp() {
        database = Mockito.mock(MySQLDatabase.class);
        mySQLFoodOrderPersistence = Mockito.mock(MySQLFoodOrderPersistence.class);
        foodOrderFactory = new FoodOrderFactory();
    }

    @Test
    void createOrderPersistenceNotNull() throws SQLException {
        IOrderPersistence orderPersistence = foodOrderFactory.createOrderPersistence(database);
        assertNotNull(orderPersistence);
    }

    @Test
    void createOrderPersistence() throws SQLException {
        IOrderPersistence orderPersistence = foodOrderFactory.createOrderPersistence(database);
        assertTrue(orderPersistence instanceof MySQLFoodOrderPersistence);
    }

    @Test
    void createOrderServiceNotNull() {
        IOrderService orderService = foodOrderFactory.createOrderService(mySQLFoodOrderPersistence);
        assertNotNull(orderService);
    }

    @Test
    void createOrderService() {
        IOrderService orderService = foodOrderFactory.createOrderService(mySQLFoodOrderPersistence);
        assertTrue(orderService instanceof OrderService);
    }

    @Test
    void createOrderNotNull() {
        IOrder order = foodOrderFactory.createOrder();
        assertNotNull(order);
    }

    @Test
    void createOrder() {
        IOrder order = foodOrderFactory.createOrder();
        assertTrue(order instanceof Order);
    }

    @Test
    void createOrderItemNotNull() {
        IOrderItem orderItem = foodOrderFactory.createOrderItem();
        assertNotNull(orderItem);
    }

    @Test
    void createOrderItem() {
        IOrderItem orderItem = foodOrderFactory.createOrderItem();
        assertTrue(orderItem instanceof OrderItem);
    }
}