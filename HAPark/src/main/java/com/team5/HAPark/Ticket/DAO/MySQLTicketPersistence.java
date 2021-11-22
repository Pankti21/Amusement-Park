package com.team5.HAPark.Ticket.DAO;

import com.team5.HAPark.Ticket.Ticket;
import database.mysql.MySQLDatabase;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLTicketPersistence implements ITicketPersistence{

    MySQLDatabase database;

    public MySQLTicketPersistence(MySQLDatabase database) {
        this.database = database;
    }
    public Ticket loadTicket(String type) throws SQLException {

        Ticket ticket = null;
        Statement statement = null;
        ResultSet resultSet = null;
        Connection connection = database.getConnection();

        try {

            statement = connection.createStatement();
            resultSet =  statement.executeQuery("SELECT * FROM ticket WHERE ticket_type = "+type+";");

            while(resultSet.next()){
                String ticketType = resultSet.getString("ticket_type");
                double ticketPrice = resultSet.getDouble("ticket_price");
                ticket = new Ticket(ticketType,ticketPrice);
            }

        } finally {

            try{
                if (resultSet != null){
                    resultSet.close();
                }
                if (statement != null){
                    statement.close();
                }
            } catch (SQLException e){
                e.printStackTrace();
            }
        }

        return ticket;
    }


}
