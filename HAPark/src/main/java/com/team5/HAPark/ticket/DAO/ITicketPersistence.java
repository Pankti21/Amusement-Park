package com.team5.HAPark.ticket.DAO;

import com.team5.HAPark.ticket.Ticket;

import java.sql.SQLException;
import java.util.List;

public interface ITicketPersistence {
    Ticket loadTicket(String type) throws SQLException;


    List<Ticket> getAllTickets()  throws SQLException;
}
