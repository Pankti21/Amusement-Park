package com.team5.HAPark.Ticket;

import com.team5.HAPark.Ticket.DAO.ITicketPersistence;

import java.sql.SQLException;

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


}
