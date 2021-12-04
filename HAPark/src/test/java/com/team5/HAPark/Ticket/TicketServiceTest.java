//package com.team5.HAPark.Ticket;
/*
import com.team5.HAPark.Ticket.*;
import com.team5.HAPark.Ticket.Ticket;
import com.team5.HAPark.Ticket.TicketOrderItem;
import com.team5.HAPark.Ticket.DAO.ITicketPersistence;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.SQLException;

//import static org.junit.jupiter.api.Assertions.*;


class TicketServiceTest {

    static TicketService ticketService;
    static ITicketPersistence ticketPersistence;

    @BeforeAll
    static void init() throws SQLException {

        pizza = new tic("pizza","1",5, 4);
        menu = new Menu();
        menu.addFoodToMenu(pizza);

        ITicketPersistence ticketPersistenceMock = Mockito.mock(ITicketPersistence.class);
        Mockito.when(ticketPersistenceMock.loadTicket()).thenReturn(menu);

        ticketService = new TicketService(ticketPersistence);
    }

    @Test
    void getTicket() throws SQLException {
        assertEquals(pizza,foodService.getFood("1"));
    }

    @Test
    void getAllTickets() throws SQLException {
        assertNull(foodService.getFood("2"));
    }

    @Test
    void getMenu() throws SQLException {
        assertEquals(menu, foodService.getMenu());
    }
}*/