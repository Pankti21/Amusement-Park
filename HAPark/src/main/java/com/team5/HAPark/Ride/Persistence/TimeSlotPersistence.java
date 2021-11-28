package com.team5.HAPark.Ride.Persistence;

import com.team5.HAPark.Ride.Model.Ride;
import com.team5.HAPark.Ride.Model.RideService;

import java.sql.SQLException;
import java.util.List;

public class TimeSlotPersistence {
    RideService rideService=new RideService();
    void getMaxOccupancy() throws SQLException {
        int maxOccupancy;
        List<Ride> rides=rideService.getAllRides();
        for (Ride ride:rides){
            maxOccupancy=ride.getMaxOccupancy();
        }
    }

}
