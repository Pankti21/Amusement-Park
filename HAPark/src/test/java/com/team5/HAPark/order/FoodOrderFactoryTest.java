package com.team5.HAPark.order;

import com.team5.HAPark.order.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FoodOrderFactoryTest {

    private FoodOrderFactory foodOrderFactory;

    @BeforeEach
    void setUp() {
        foodOrderFactory = new FoodOrderFactory();
    }

    @Test
    void createOrderServiceNotNull() {
        IOrderService orderService = foodOrderFactory.createOrderService();
        assertNotNull(orderService);
    }

    @Test
    void createOrderService() {
        IOrderService orderService = foodOrderFactory.createOrderService();
        assertTrue(orderService instanceof OrderService);
    }

}