package com.team5.HAPark.order;

import com.team5.HAPark.order.DAO.IOrderPersistence;
import com.team5.HAPark.order.DAO.MySQLFoodOrderPersistence;
import com.team5.HAPark.order.model.*;
import com.team5.HAPark.database.mysql.MySQLDatabase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

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
    void createOrderPersistenceNotNull() {
        IOrderPersistence orderPersistence = foodOrderFactory.createOrderPersistence(database);
        assertNotNull(orderPersistence);
    }

    @Test
    void createOrderPersistence() {
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