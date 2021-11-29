package com.team5.HAPark.Ticket;

import com.team5.HAPark.Ticket.DAO.ITicketPersistence;
import com.team5.HAPark.Ticket.model.Ticket;
import com.team5.HAPark.Ticket.model.TicketOrderItem;
import com.team5.HAPark.Ticket.model.TicketService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class TicketServiceTest {

    static TicketService ticketService;
    static ITicketPersistence ticketPersistence;
    static TicketOrderItem ticketOrderItem;
    static Ticket ticket1;
    static Ticket ticket2;
    static Ticket ticket3;
    static Ticket ticket4;
    static List<Ticket> tickets = new ArrayList<>();

   @BeforeAll
    static void init() throws SQLException {
        ticket1 = new Ticket("Child",10);
        ticket2 = new Ticket("Adult",15);
        ticket3 = new Ticket("Adult",15);
        ticketOrderItem = new TicketOrderItem(ticket1,2);
        ITicketPersistence ticketPersistenceMock = Mockito.mock(ITicketPersistence.class);
        Mockito.when(ticketPersistenceMock.loadTicket("Child")).thenReturn(ticket1);
       Mockito.when(ticketPersistenceMock.loadTicket("Adult")).thenReturn(ticket2);
        ticketService = new TicketService(ticketPersistenceMock);
        tickets.add(ticket1);
        tickets.add(ticket2);

        Mockito.when(ticketPersistenceMock.getAllTickets()).thenReturn(tickets);
    }

    @Test
    void getChildTicket() throws SQLException {
        assertEquals(ticket1,ticketService.getTicket("Child"));
    }

    @Test
    void getAdultTicket() throws SQLException {
        assertEquals(ticket2,ticketService.getTicket("Adult"));
    }
    @Test
    void getAllTickets() throws  SQLException {
        assertEquals(4,ticketService.getAllTickets().size());
    }

    @Test
    void getAllMoreTickets() throws  SQLException {
        tickets.add(ticket3);
        assertEquals(5,ticketService.getAllTickets().size());
    }

    @Test
    void getAllFiveTickets() throws  SQLException {
        tickets.add(ticket3);
        tickets.add(ticket4);
        assertEquals(4,ticketService.getAllTickets().size());
    }
}