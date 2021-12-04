package com.team5.HAPark.Ticket.DAO;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TicketPersistenceFactoryTest {

    private ITicketPersistence ticketPersistence1;
    private ITicketPersistence ticketPersistence2;

    @BeforeEach
    void setUp() {
        TicketPersistenceFactory ticketPersistenceFactory1 = new TicketPersistenceFactory();
        TicketPersistenceFactory ticketPersistenceFactory2 = new TicketPersistenceFactory();

        ticketPersistence1 = ticketPersistenceFactory1.createTicketPersistence();
        ticketPersistence2 = ticketPersistenceFactory2.createTicketPersistence();
    }

    @Test
    void createTicketPersistenceSameInstance() {
        assertEquals(ticketPersistence1,ticketPersistence2);
    }

    @Test
    void createTicketPersistenceNotNull() {
        assertNotNull(ticketPersistence1);
    }
}