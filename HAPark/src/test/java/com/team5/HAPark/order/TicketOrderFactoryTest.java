package com.team5.HAPark.order;

import com.team5.HAPark.order.DAO.IOrderPersistence;
import com.team5.HAPark.order.DAO.MySQLTicketOrderPersistence;
import com.team5.HAPark.order.model.*;
import com.team5.HAPark.database.mysql.MySQLDatabase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class TicketOrderFactoryTest {

    private TicketOrderFactory ticketOrderFactory;
    private MySQLDatabase database;
    private MySQLTicketOrderPersistence mySQLTicketOrderPersistence;


    @BeforeEach
    void setUp() {
        database = Mockito.mock(MySQLDatabase.class);
        mySQLTicketOrderPersistence = Mockito.mock(MySQLTicketOrderPersistence.class);
        ticketOrderFactory = new TicketOrderFactory();
    }

    @Test
    void createOrderPersistenceNotNull() {
        IOrderPersistence orderPersistence = ticketOrderFactory.createOrderPersistence(database);
        assertNotNull(orderPersistence);
    }

    @Test
    void createOrderPersistence() {
        IOrderPersistence orderPersistence = ticketOrderFactory.createOrderPersistence(database);
        assertTrue(orderPersistence instanceof MySQLTicketOrderPersistence);
    }

    @Test
    void createOrderServiceNotNull() {
        IOrderService orderService = ticketOrderFactory.createOrderService(mySQLTicketOrderPersistence);
        assertNotNull(orderService);
    }

    @Test
    void createOrderService() {
        IOrderService orderService = ticketOrderFactory.createOrderService(mySQLTicketOrderPersistence);
        assertTrue(orderService instanceof OrderService);
    }

    @Test
    void createOrderNotNull() {
        IOrder order = ticketOrderFactory.createOrder();
        assertNotNull(order);
    }

    @Test
    void createOrder() {
        IOrder order = ticketOrderFactory.createOrder();
        assertTrue(order instanceof Order);
    }

    @Test
    void createOrderItemNotNull() {
        IOrderItem orderItem = ticketOrderFactory.createOrderItem();
        assertNotNull(orderItem);
    }

    @Test
    void createOrderItem() {
        IOrderItem orderItem = ticketOrderFactory.createOrderItem();
        assertTrue(orderItem instanceof OrderItem);
    }
}