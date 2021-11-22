package com.team5.HAPark.Ticket.DAO;

import com.team5.HAPark.database.mysql.MySQLDatabase;

public class MySQLTicketPersistence implements ITicketPersistence{

    MySQLDatabase database;

    public MySQLTicketPersistence(MySQLDatabase database) {
        this.database = database;
    }
}
