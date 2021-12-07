package com.team5.HAPark.reserveRide.model;

import com.team5.HAPark.ride.model.IRideService;
import com.team5.HAPark.ride.model.Ride;
import com.team5.HAPark.ride.model.RideServiceFactory;
import com.team5.HAPark.ride.persistence.RidePersistenceFactory;
import com.team5.HAPark.reserveRide.persistence.IRideReservePersistence;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class RideReserveService implements IRideReserveService {

    private IRideReservePersistence rideReservePersistence;
    private IRideService rideService= new RideServiceFactory().getRideService("RIDESERVICE");

    public RideReserveService(IRideReservePersistence rideReservePersistence) {
        this.rideReservePersistence=rideReservePersistence;
    }

    public RideReserveService() {
    }

    public void reserve(int rideId, int timeslotId, int reserveSeats) throws SQLException {
        IRideReservePersistence rideReservePersistence = new RidePersistenceFactory().createRideReservePersistence();
        rideReservePersistence.addReservationToDB(rideId,timeslotId,reserveSeats);
    }

    //reserves number of seats entered by the user for the given ride id and timeslot id
    public int reserveSeats(int rideId, int timeslotId, int seats) throws SQLException {
        int availability=rideReservePersistence.getRideAvailability(rideId,timeslotId);
        availability-=seats;
        rideReservePersistence.updateRideAvailability(rideId,timeslotId,availability);
        return availability;
    }

    public List<RideReserve> getReservations() throws SQLException {
        List<RideReserve> ridesReserved=rideReservePersistence.getReservations();
        return ridesReserved;
    }

    public List<String> getReservedRideNames() throws SQLException {
        List<String> ReservedRideNames=new ArrayList<>();
        List<RideReserve> ridesReserved=rideReservePersistence.getReservations();
        for (RideReserve rideReserve:ridesReserved){
            int rideId=rideReserve.getRideId();
            Ride ride=rideService.getRide(rideId);
            String rideName=ride.getName();
            ReservedRideNames.add(rideName);
        }
        return ReservedRideNames;
    }

}
