package com.team5.HAPark.Order;

import com.team5.HAPark.Order.model.*;
import com.team5.HAPark.Ticket.ITicketService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class TicketOrderFactoryTest {

    private TicketOrderFactory ticketOrderFactory;

    @BeforeEach
    void setUp() {
        ITicketService ticketService = Mockito.mock(ITicketService.class);
        ticketOrderFactory = new TicketOrderFactory(ticketService);
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