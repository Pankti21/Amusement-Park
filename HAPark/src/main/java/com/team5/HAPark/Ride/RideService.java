package com.team5.HAPark.Ride;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class RideService {
    private List<Ride> rides= Arrays.asList(
            new Ride("1","RollarCoaster","demo description"),
            new Ride("2","WaterSlide","demo description2")
            );

    public List<Ride> getAllRides(){
        return rides;
    }

    public Ride getRide(String id){
        Ride r=new Ride();
        for(Ride ride:rides) {
            if (ride.getId().equals(id))
                r=ride;
        }
        return r;
    }
}
