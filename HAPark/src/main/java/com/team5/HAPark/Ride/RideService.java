package com.team5.HAPark.Ride;

import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class RideService {
    private List<Ride> rides= new ArrayList<>(Arrays.asList(
            new Ride(1,"RollarCoaster","Ground",5, LocalTime.of(01,30,33)),
            new Ride(2,"WaterSlide","Water",6,LocalTime.of(00,10,00))
            ));

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

    public void addRide(Ride ride) {
        rides.add(ride);
    }
}
