package com.team5.HAPark.ride.model;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public class RideServiceFactory{
    public IRideService getRideService(String rideService){
        if(rideService==null){
            return null;
        }
        if(rideService.equalsIgnoreCase("RIDESERVICE")){
            return new RideService();
        }
        return null;
    }
}
