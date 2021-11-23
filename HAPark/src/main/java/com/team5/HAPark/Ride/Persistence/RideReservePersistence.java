package com.team5.HAPark.Ride.Persistence;

import com.team5.HAPark.Ride.Model.RideReserve;
import com.team5.HAPark.Ride.Model.TimeSlot;
import com.team5.HAPark.database.mysql.MySQLDatabase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Slf4j
public class RideReservePersistence {
    public void addReservationToDB(int rideId,int timeSlotId,int seats) throws SQLException {
        String auth =  SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        MySQLDatabase mySQLDatabase=new MySQLDatabase();
        Connection con=mySQLDatabase.getConnection();
        Statement stmt= con.createStatement();
        stmt.executeUpdate("INSERT INTO ride_reserve (user_mail_id,ride_id,timeslot_id,seats) VALUES (\""+auth+"\","+rideId+","+timeSlotId+","+seats+");");
        mySQLDatabase.close();
        stmt.close();
        con.close();
    }

    //Get seats available for a given ride at a given timeslot
    public int getRideAvailability(int rideId, int timeSlotId) throws SQLException {
        MySQLDatabase mySQLDatabase=new MySQLDatabase();
        IRidePersistence ridePersistence= new RidePersistence(mySQLDatabase);
        TimeSlot timeSlot=new TimeSlot();
        timeSlot=ridePersistence.getRideTimeSlot(rideId);
        HashMap<Integer,Integer> map = timeSlot.getMap();
        int availability=map.get(timeSlotId);
        mySQLDatabase.close();
        return availability;
    }

    //Update reserved seats by user in com.team5.HAPark.database
    public void updateRideAvailability(int rideId, int timeslotId, int availability) throws SQLException {
        MySQLDatabase mySQLDatabase=new MySQLDatabase();
        Connection con=mySQLDatabase.getConnection();
        Statement stmt= con.createStatement();
        stmt.executeUpdate("UPDATE ride_timeslot SET availability="+availability+" WHERE ride_id="+rideId+" AND timeslot_id="+timeslotId);
        mySQLDatabase.close();
        stmt.close();
        con.close();
    }

    public List<RideReserve> getReservations() throws SQLException {
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        String username = currentUser.getName();
        MySQLDatabase mySQLDatabase=new MySQLDatabase();
        Connection con=mySQLDatabase.getConnection();
        Statement stmt= con.createStatement();
        ResultSet rs= stmt.executeQuery("SELECT * FROM ride_reserve WHERE user_mail_id=\""+username+"\"");
        List<RideReserve> ridesReserved=new ArrayList<>();
        while (rs.next()){
            RideReserve rideReserve=new RideReserve();
            rideReserve.setRideId(rs.getInt("ride_id"));
            rideReserve.setTimeslotId(rs.getInt("timeslot_id"));
            rideReserve.setReserveSeats(rs.getInt("seats"));
            ridesReserved.add(rideReserve);
        }
        mySQLDatabase.close();
        con.close();
        stmt.close();
        return ridesReserved;
    }

}
