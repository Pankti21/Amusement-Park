package com.team5.HAPark.Ride.Persistence.WaitTime;

import com.team5.HAPark.database.mysql.MySQLDatabase;
import java.sql.*;

public class WaitTimePersistence implements IWaitTimePersistence{

    MySQLDatabase mySQLDatabase;

    public WaitTimePersistence() {
    }

    //Gets maximum capacity of a ride
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

    //Gets the time duration for which a ride will last
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
}
