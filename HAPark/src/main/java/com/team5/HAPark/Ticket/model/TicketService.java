package com.team5.HAPark.Ticket.model;

import com.team5.HAPark.Ticket.DAO.ITicketPersistence;
import com.team5.HAPark.Ticket.model.Ticket;

import java.sql.SQLException;
import java.util.List;


public class TicketService {

    private ITicketPersistence ticketPersistence;

    public TicketService(ITicketPersistence ticketPersistence) {
        this.ticketPersistence = ticketPersistence;
    }

    public Ticket getTicket(String ticketType) throws SQLException {
        Ticket ticket = null;
        ticket = ticketPersistence.loadTicket(ticketType);

        return ticket;
    }

       public List<Ticket> getAllTickets() throws SQLException {
        List<Ticket> Tickets =ticketPersistence.getAllTickets();
        return Tickets;
    }
    }



