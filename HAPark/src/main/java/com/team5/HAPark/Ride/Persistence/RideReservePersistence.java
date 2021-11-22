package com.team5.HAPark.Ride.Persistence;

import com.team5.HAPark.Ride.Model.TimeSlot;
import com.team5.HAPark.database.mysql.MySQLDatabase;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class RideReservePersistence {
    public void addReservationToDB(int rideId,int timeSlotId,int seats) throws SQLException {
        String auth =  SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        MySQLDatabase mySQLDatabase=new MySQLDatabase();
        Connection con=mySQLDatabase.getConnection();
        Statement stmt= con.createStatement();
        stmt.executeUpdate("INSERT INTO ride_reserve (user_mail_id,ride_id,timeslot_id,seats) VALUES (\""+auth+"\","+rideId+","+timeSlotId+","+seats+");");
        mySQLDatabase.close();
    }

    //Get seats available for a given ride at a given timeslot
    public int getRideAvailability(int rideId, int timeSlotId) throws SQLException {
        IRidePersistence ridePersistence= new RidePersistence(new MySQLDatabase());
        TimeSlot timeSlot=new TimeSlot();
        timeSlot=ridePersistence.getRideTimeSlot(rideId);
        HashMap<Integer,Integer> map = timeSlot.getMap();
        int availability=map.get(timeSlotId);
        return availability;
    }

    //Update reserved seats by user in com.team5.HAPark.database
    public void updateRideAvailability(int rideId, int timeslotId, int availability) throws SQLException {

        MySQLDatabase mySQLDatabase=new MySQLDatabase();
        Connection con=mySQLDatabase.getConnection();
        Statement stmt= con.createStatement();
        stmt.executeUpdate("UPDATE ride_timeslot SET availability="+availability+" WHERE ride_id="+rideId+" AND timeslot_id="+timeslotId);
    }
}
