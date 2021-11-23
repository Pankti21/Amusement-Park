package com.team5.HAPark.Ride.Model;

import java.sql.SQLException;
import java.util.List;

public interface IRideReserveService {
    void reserve(int rideId, int timeslotId, int reserveSeats) throws SQLException;
    void reserveSeats(int rideId, int timeslotId, int seats) throws SQLException;
    List<RideReserve> getReservations() throws SQLException;
}
