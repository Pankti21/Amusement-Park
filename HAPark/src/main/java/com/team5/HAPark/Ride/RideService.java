package com.team5.HAPark.Ride;

import org.springframework.stereotype.Service;

import java.sql.Time;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

@Service
public class RideService {
    private List<Ride> rides= Arrays.asList(
            new Ride(1,"RollarCoaster","Ground",5, LocalTime.of(01,30,33))
            //new Ride("2","WaterSlide","demo description2")
            );

    public List<Ride> getAllRides(){
        return rides;
    }

    public Ride getRide(int id){
        Ride r=new Ride();
        for(Ride ride:rides) {
            if (ride.getId() == id)
                r=ride;
        }
        return r;
    }
}
