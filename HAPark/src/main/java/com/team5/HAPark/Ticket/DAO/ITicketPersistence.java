package com.team5.HAPark.Ticket.DAO;

import com.team5.HAPark.Ticket.Ticket;

import java.sql.SQLException;

public interface ITicketPersistence {
    Ticket loadTicket(String type) throws SQLException;
}
