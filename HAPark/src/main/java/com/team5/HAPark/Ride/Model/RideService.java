package com.team5.HAPark.Ride.Model;

import com.team5.HAPark.Ride.Persistence.IRidePersistence;
import com.team5.HAPark.Ride.Persistence.RidePersistence;
import com.team5.HAPark.database.mysql.MySQLDatabase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.*;


@Slf4j
@Service
public class RideService implements IRideService {

    private IRidePersistence ridePersistence;

    public RideService(IRidePersistence ridePersistence) throws SQLException {
        this.ridePersistence = ridePersistence;
    }

    public List<Ride> getAllRides() throws SQLException {
        log.info("Hello world.");
        List<Ride> Rides=ridePersistence.getAllRides();
        for(Ride ride:Rides) {
            log.info("ride id: {}", ride.getId());
        }
        return Rides;
    }

    public List<HashMap<Integer,Integer>> getAllTimeSlots() throws SQLException{
        List<HashMap<Integer,Integer>> maps= new ArrayList<>();
        maps=ridePersistence.getAllTimeSlots();
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
        RidePersistence ridePersistence=new RidePersistence(new MySQLDatabase());

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
        RidePersistence ridePersistence=new RidePersistence(new MySQLDatabase());
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

    //reserves number of seats entered by the user for the given ride id and timeslot id
    public void reserveSeats(int rideId, int timeslotId, int seats) throws SQLException {
        int availability=ridePersistence.getRideAvailability(rideId,timeslotId);
        availability-=seats;
        ridePersistence.updateRideAvailability(rideId,timeslotId,availability);
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
