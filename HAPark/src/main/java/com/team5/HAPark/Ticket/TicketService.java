package com.team5.HAPark.Ticket;

import com.team5.HAPark.Ticket.DAO.ITicketPersistence;

import java.util.List;

public class TicketService {

    private ITicketPersistence ticketPersistence;

    public TicketService(ITicketPersistence ticketPersistence) {
        this.ticketPersistence = ticketPersistence;
    }

    public Ticket getTicket(String ticketType) {
        return null;
    }

    public List<Ticket> getAllTickets() {
        return null;
    }
}
