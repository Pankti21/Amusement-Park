package com.team5.HAPark.order;

import com.team5.HAPark.order.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TicketOrderFactoryTest {

    private TicketOrderFactory ticketOrderFactory;

    @BeforeEach
    void setUp() {
        ticketOrderFactory = new TicketOrderFactory();
    }

    @Test
    void createOrderServiceNotNull() {
        IOrderService orderService = ticketOrderFactory.createOrderService();
        assertNotNull(orderService);
    }

    @Test
    void createOrderService() {
        IOrderService orderService = ticketOrderFactory.createOrderService();
        assertTrue(orderService instanceof OrderService);
    }

}