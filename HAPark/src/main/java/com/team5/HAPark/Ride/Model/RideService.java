package com.team5.HAPark.Ride.Model;

import com.team5.HAPark.Ride.Persistence.IRidePersistence;
import com.team5.HAPark.Ride.Persistence.RidePersistence;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;


@Slf4j
@Service
public class RideService implements IRideService {

    private IRidePersistence ridePersistence= new RidePersistence();

    public RideService(IRidePersistence ridePersistence) {
        this.ridePersistence = ridePersistence;
    }

    public List<Ride> getAllRides() throws SQLException {
        log.info("Hello world.");
        List<Ride> Rides=ridePersistence.getAllRides();
        for(Ride ride:Rides) {
            log.info("ride id: {}", ride.getId());
            log.info("ride name: {}", ride.getName());
            log.info("ride type: {}", ride.getType());
            log.info("ride max_occupancy: {}", ride.getMaxOccupancy());
            log.info("ride duration: {}", ride.getDuration());
            log.info("timeslot: {}",ride.getTimeSlot().map.get(1));
            log.info("timeslot: {}",ride.getTimeSlot().map.get(2));
            log.info("timeslot: {}",ride.getTimeSlot().map.get(3));
        }
        return Rides;
    }

<<<<<<< HEAD
    public List<String> getAllRideNames() throws SQLException {
        List<String> names= new ArrayList<>();
=======
    public List<Ride> getAllGroundRides() throws SQLException {
        RidePersistence ridePersistence=new RidePersistence();
>>>>>>> feature/rides2
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
        RidePersistence ridePersistence=new RidePersistence();
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

   public Ride reserveRide(int rideId,int timeSlotId) throws SQLException {
        int availability=ridePersistence.getRideAvailability(rideId,timeSlotId);
        availability-=availability;
        //ridePersistence.updateAvailability(int qty);
        return null;
        }


    private List<Ride> rides= new ArrayList<>(Arrays.asList(
           // new Ride(1,"RollarCoaster","Ground",5),
            //new Ride(2,"WaterSlide","Water",6,LocalTime.of(00,10,00))
    ));




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
