package com.team5.HAPark.Ticket;

import com.team5.HAPark.Ticket.DAO.ITicketPersistence;

import java.sql.SQLException;
import java.util.ArrayList;
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

    public List<String> getAllTickets() throws SQLException {
        List<String> tickets= new ArrayList<>();
        List<Ticket> Tickets =ticketPersistence.getAllTickets();
        for(Ticket ticket: Tickets) {
            tickets.add(ticket.getTicketType());
        }
        return tickets;
    }
    }

