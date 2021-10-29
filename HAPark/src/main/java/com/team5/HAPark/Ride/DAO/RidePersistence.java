package com.team5.HAPark.Ride.DAO;

import com.team5.HAPark.Ride.Ride;
import database.mysql.MySQLDatabase;
import lombok.extern.slf4j.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class RidePersistence implements IRidePersistence{
    MySQLDatabase mySQLDatabase = new MySQLDatabase();

    @Override
    public Ride getRide(int id) throws SQLException {
        Connection con=mySQLDatabase.getConnection();
        Statement stmt= con.createStatement();
        ResultSet rs= stmt.executeQuery("SELECT * FROM rides_info WHERE ride_id="+id+";");
        Ride r = new Ride();
        while (rs.next()){
                r.setId(rs.getInt("ride_id"));
                r.setName(rs.getString("ride_name"));
                r.setType(rs.getString("ride_type"));
                r.setMaxOccupancy(rs.getInt("max_occupancy"));
                r.setDuration(LocalTime.of(00,00,00));
            }
        return r;
    }

    @Override
    public List<Ride> getAllRides() throws SQLException {
        List<Ride> Rides= new ArrayList<Ride>();
        Connection con=mySQLDatabase.getConnection();
        Statement stmt= con.createStatement();
        ResultSet rs= stmt.executeQuery("SELECT * FROM rides_info;");
        //String ride_name="";
        while (rs.next()){
            Ride r = new Ride();
            r.setId(rs.getInt("ride_id"));
            r.setName(rs.getString("ride_name"));
            r.setType(rs.getString("ride_type"));
            r.setMaxOccupancy(rs.getInt("max_occupancy"));
            r.setDuration(LocalTime.of(00,00,00));
            Rides.add(r);
        }
        return Rides;
    }
}
