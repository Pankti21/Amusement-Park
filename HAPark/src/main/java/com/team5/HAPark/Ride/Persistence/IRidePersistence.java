package com.team5.HAPark.Ride.Persistence;

import com.team5.HAPark.Ride.Model.Ride;
import com.team5.HAPark.Ride.Model.TimeSlot;

import java.sql.SQLException;
import java.util.List;

public interface IRidePersistence {

    Ride getRide(int id) throws SQLException;
    List<Ride> getAllRides() throws SQLException;
    TimeSlot getRideavailability(int id) throws SQLException;
}

