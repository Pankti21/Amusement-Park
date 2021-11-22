package com.team5.HAPark.Ride.Model;

import com.team5.HAPark.Ride.Persistence.RideReservePersistence;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class RideReserveService {

    public void reserve(int rideId, int timeslotId, int reserveSeats) throws SQLException {
        RideReservePersistence rideReservePersistence=new RideReservePersistence();
        rideReservePersistence.addReservationToDB(rideId,timeslotId,reserveSeats);
    }
}
