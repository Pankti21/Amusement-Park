package com.team5.HAPark.ticket;

import com.team5.HAPark.ticket.model.Ticket;
import com.team5.HAPark.ticket.model.TicketOrderItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TicketOrderItemTest {
    private TicketOrderItem ticketOrderItem;

    @BeforeEach
    void setUp() {
        Ticket ticket = new Ticket("item",15);
        ticketOrderItem = new TicketOrderItem(ticket,3);
    }

    @Test
    void getTicketType() {
        assertEquals("item",ticketOrderItem.getTicketType());
    }

    @Test
    void setTicketType() {
        ticketOrderItem.setTicketType("new type");
        assertEquals("new type",ticketOrderItem.getTicketType());
    }

    @Test
    void getTicketPrice() {
        assertEquals(15,ticketOrderItem.getTicketPrice());
    }

    @Test
    void setTicketPrice() {
        ticketOrderItem.setTicketPrice(5);
        assertEquals(5,ticketOrderItem.getTicketPrice());
    }

}