package com.team5.HAPark.Ticket.DAO;

import com.team5.HAPark.Database.mysql.MySQLDatabase;

public class TicketPersistenceFactory implements ITicketPersistenceFactory {

    private static ITicketPersistence ticketPersistence;
    @Override
    public ITicketPersistence createTicketPersistence() {
        if (ticketPersistence == null) {
            ticketPersistence = new MySQLTicketPersistence(MySQLDatabase.getInstance());
        }
        return ticketPersistence;
    }
}
