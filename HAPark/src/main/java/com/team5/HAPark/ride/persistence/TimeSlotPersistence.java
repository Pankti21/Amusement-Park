package com.team5.HAPark.ride.persistence;

import com.team5.HAPark.ride.model.Ride;
import com.team5.HAPark.ride.model.RideService;

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
