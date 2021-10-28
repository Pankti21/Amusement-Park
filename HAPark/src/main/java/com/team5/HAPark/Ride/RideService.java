package com.team5.HAPark.Ride;

import com.team5.HAPark.Ride.DAO.RidePersistence;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class RideService {

    private List<Ride> rides= new ArrayList<>(Arrays.asList(
            new Ride(1,"RollarCoaster","Ground",5, LocalTime.of(01,30,33)),
            new Ride(2,"WaterSlide","Water",6,LocalTime.of(00,10,00))
            ));


    public List<Ride> getAllRides() {
        log.info("Hello world.");
        return rides;
    }

    public void test() throws SQLException {
        RidePersistence ridePersistence = new RidePersistence();
        String ride_name=ridePersistence.getRide(1);
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

    public void updateRide(Ride ride,int id) {
        for(Ride r:rides){
            if (r.getId() == id)
                rides.set(id-1,ride);
        }
    }

    public void deleteRide(int id) {
        for(Ride r:rides){
            if (r.getId() == id)
                rides.remove(id-1);
        }
    }
}
