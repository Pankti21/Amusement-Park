package com.team5.HAPark.Ride.DAO;

import database.mysql.MySQLDatabase;
import lombok.extern.slf4j.*;

import javax.swing.plaf.nimbus.State;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Slf4j
public class RidePersistence implements IRidePersistence{
    MySQLDatabase mySQLDatabase = new MySQLDatabase();

    @Override
    public String getRide(int id) throws SQLException {
        Connection con=mySQLDatabase.getConnection();
        Statement stmt= con.createStatement();
        ResultSet rs= stmt.executeQuery("SELECT * FROM rides_info WHERE ride_id=1;");
        String ride_name="";
        while (rs.next()){
            ride_name = rs.getString("ride_name");
        }
        log.info("ride name: {}",ride_name);
        return ride_name;
    }
}
