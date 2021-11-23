package com.team5.HAPark.Ride.Model;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public class WaitTime {

    RideService rideService=new RideService();

    public WaitTime() throws SQLException {
    }

    void method() throws SQLException {
        Ride ride=rideService.getRide(1);
        List<HashMap<Integer,Integer>> ts=rideService.getAllTimeSlots();
    }
}
