package com.team5.HAPark.ride.model;

import com.team5.HAPark.ride.persistence.IRidePersistence;
import com.team5.HAPark.ride.persistence.RidePersistenceFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.*;

@Slf4j
@Service
public class RideService implements IRideService {

    private IRidePersistence ridePersistence = new RidePersistenceFactory().createRidePersistence();

    public RideService(IRidePersistence ridePersistence) throws SQLException {
        this.ridePersistence = ridePersistence;
    }

    public RideService() {
    }

    public List<Ride> getAllRides() throws SQLException {
        log.info("Hello world.");
        List<Ride> Rides=ridePersistence.getAllRides();
        for(Ride ride:Rides) {
            log.info("ride id: {}", ride.getId());
        }
        return Rides;
    }

    public List<HashMap<Integer,Integer>> getAllTimeSlots() throws SQLException {
        List<HashMap<Integer,Integer>> maps = ridePersistence.getAllTimeSlots();
        return maps;
    }

    public List<String> getAllRideNames() throws SQLException {
        List<String> names = new ArrayList<>();
        List<Ride> Rides=ridePersistence.getAllRides();
        for (Ride ride:Rides){
            names.add(ride.getName());
        }
        return names;

    }

    public List<Ride> getAllGroundRides() throws SQLException {
        List<Ride> Rides=ridePersistence.getAllRides();
        List<Ride> groundRides=new ArrayList<>();
        for(Ride ride:Rides) {
            if(Objects.equals(ride.getType(), "Ground")){
                groundRides.add(ride);
            }
        }
        return groundRides;
    }

    public List<Ride> getAllWaterRides() throws SQLException {
        List<Ride> Rides=ridePersistence.getAllRides();
        List<Ride> waterRides=new ArrayList<>();
        for(Ride ride:Rides) {
            if(Objects.equals(ride.getType(), "Water")){
                waterRides.add(ride);
            }
        }
        return waterRides;
    }

    public Ride getRide(int id) throws SQLException {
        Ride ride=ridePersistence.getRide(id);
        return ride;
    }

    public String getTimeSlotName(int timeslotId) {
        if(timeslotId==1){
            return "Morning timeslot at 10:00AM";
        }
        if (timeslotId==2){
            return "Afternoon timeslot at 2:00PM";
        }
            return "Evening timeslot at 6:00PM";
    }
}
