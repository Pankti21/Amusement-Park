package com.team5.HAPark.Ride.Persistence;

import com.team5.HAPark.database.mysql.MySQLDatabase;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class RideReservePersistence {
    public void addReservationToDB(int rideId,int timeSlotId,int seats) throws SQLException {
        String auth =  SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        MySQLDatabase mySQLDatabase=new MySQLDatabase();
        Connection con=mySQLDatabase.getConnection();
        Statement stmt= con.createStatement();
        stmt.executeUpdate("INSERT INTO ride_reserve (user_mail_id,ride_id,timeslot_id,seats) VALUES (\""+auth+"\","+rideId+","+timeSlotId+","+seats+");");
        mySQLDatabase.close();
    }
}
