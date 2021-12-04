package com.team5.HAPark.ticket;

import com.team5.HAPark.ticket.model.Ticket;

import java.sql.SQLException;
import java.util.List;

public interface ITicketService {
    Ticket getTicket(String ticketType) throws SQLException;

    List<Ticket> getAllTickets() throws SQLException;
}
