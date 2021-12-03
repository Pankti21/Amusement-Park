package com.team5.HAPark.ride.Model;

import com.team5.HAPark.ride.Persistence.IRideReservePersistence;
import com.team5.HAPark.ride.Persistence.RidePersistenceFactory;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class RideReserveService implements IRideReserveService{

    IRideReservePersistence rideReservePersistence;

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

}
