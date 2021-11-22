package com.team5.HAPark.Ride.Model;

import com.team5.HAPark.Ride.Persistence.RideReservePersistence;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class RideReserveService {

    RideReservePersistence rideReservePersistence=new RideReservePersistence();

    public void reserve(int rideId, int timeslotId, int reserveSeats) throws SQLException {
        rideReservePersistence.addReservationToDB(rideId,timeslotId,reserveSeats);
    }

    //reserves number of seats entered by the user for the given ride id and timeslot id
    public void reserveSeats(int rideId, int timeslotId, int seats) throws SQLException {
        int availability=rideReservePersistence.getRideAvailability(rideId,timeslotId);
        availability-=seats;
        rideReservePersistence.updateRideAvailability(rideId,timeslotId,availability);
    }

}
