package com.team5.HAPark.Ride.Persistence;

import com.team5.HAPark.Ride.Model.Ride;
import com.team5.HAPark.Ride.Model.TimeSlot;
import com.team5.HAPark.database.mysql.MySQLDatabase;
import lombok.extern.slf4j.*;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Slf4j
@Component
public class RidePersistence implements IRidePersistence{
   MySQLDatabase mySQLDatabase;

    public RidePersistence(MySQLDatabase mySQLDatabase) {
        this.mySQLDatabase=mySQLDatabase;
    }

    public RidePersistence() {

    }

    @Override
    public Ride getRide(int id) throws SQLException {
        mySQLDatabase = MySQLDatabase.getInstance();
        Connection con = mySQLDatabase.getConnection();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM rides_info WHERE ride_id="+id+";");
        Ride r = new Ride();
        while (rs.next()){
                r.setId(rs.getInt("ride_id"));
                r.setName(rs.getString("ride_name"));
                r.setType(rs.getString("ride_type"));
                r.setMaxOccupancy(rs.getInt("max_occupancy"));
                r.setDuration(rs.getTime("total_duration"));
                r.setTimeSlot(getRideTimeSlot(r.getId()));
        }

        return r;
    }

    @Override
    public List<Ride> getAllRides() throws SQLException {
        List<Ride> Rides= new ArrayList<Ride>();
        mySQLDatabase = MySQLDatabase.getInstance();
        Connection con=mySQLDatabase.getConnection();
        Statement stmt= con.createStatement();
        ResultSet rs= stmt.executeQuery("SELECT * FROM rides_info;");
        while (rs.next()){
            Ride r = new Ride();
            r.setId(rs.getInt("ride_id"));
            r.setName(rs.getString("ride_name"));
            r.setType(rs.getString("ride_type"));
            r.setMaxOccupancy(rs.getInt("max_occupancy"));
            r.setDuration(rs.getTime("total_duration"));
            r.setTimeSlot(getRideTimeSlot(r.getId()));
            Rides.add(r);
        }
        return Rides;
    }

    public int getRideMaxOccupancy(int rideId) throws SQLException {
        int maxOccupancy=0;
        mySQLDatabase =  MySQLDatabase.getInstance();
        Connection con = mySQLDatabase.getConnection();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM rides_info WHERE ride_id="+rideId);
        while (rs.next()){
            maxOccupancy=rs.getInt("max_occupancy");
        }
        return maxOccupancy;
    }

    public Time getRideDuration(int rideId) throws SQLException {
        Time duration = null;
        mySQLDatabase = MySQLDatabase.getInstance();
        Connection con=mySQLDatabase.getConnection();
        Statement stmt= con.createStatement();
        ResultSet rs= stmt.executeQuery("SELECT * FROM rides_info WHERE ride_id="+rideId);
        while (rs.next()){
            duration=rs.getTime("total_duration");
        }

        return duration;
    }

    public List<HashMap<Integer,Integer>> getAllTimeSlots() throws SQLException {
        mySQLDatabase = MySQLDatabase.getInstance();
        IRidePersistence ridePersistence=new RidePersistence(mySQLDatabase);
        List<Ride> Rides = ridePersistence.getAllRides();
        List<HashMap<Integer,Integer>> maps = new ArrayList<>();
        for (Ride ride:Rides){
            maps.add(ridePersistence.getRideTimeSlot(ride.getId()).getMap());
        }
        return maps;
    }

    public TimeSlot getRideTimeSlot(int id) throws SQLException {
        mySQLDatabase = MySQLDatabase.getInstance();
        Connection con = mySQLDatabase.getConnection();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM ride_timeslot WHERE ride_id="+id);
        HashMap<Integer,Integer> map= new HashMap<>();
        while (rs.next()){
            map.put(rs.getInt("timeslot_id"),rs.getInt("availability"));
        }
        TimeSlot timeSlot=new TimeSlot(map);
        return timeSlot;
    }

}
