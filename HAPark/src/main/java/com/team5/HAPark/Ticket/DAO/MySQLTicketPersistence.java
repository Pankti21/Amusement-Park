package com.team5.HAPark.Ticket.DAO;

import database.mysql.MySQLDatabase;

public class MySQLTicketPersistence implements ITicketPersistence{

    MySQLDatabase database;

    public MySQLTicketPersistence(MySQLDatabase database) {
        this.database = database;
    }
}
