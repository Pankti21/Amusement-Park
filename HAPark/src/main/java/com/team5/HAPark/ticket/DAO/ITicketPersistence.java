package com.team5.HAPark.Ticket.DAO;

import com.team5.HAPark.Ticket.model.Ticket;

import java.sql.SQLException;
import java.util.List;

public interface ITicketPersistence {
    Ticket loadTicket(String type) throws SQLException;


    List<Ticket> getAllTickets()  throws SQLException;;
}
