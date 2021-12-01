package com.team5.HAPark.Ride.Model;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public interface IRideService {
    List<Ride> getAllRides() throws SQLException;
    Ride getRide(int id) throws SQLException;
    List<String> getAllRideNames() throws SQLException;
    List<HashMap<Integer,Integer>> getAllTimeSlots() throws SQLException;
    List<Ride> getAllGroundRides() throws SQLException;
    List<Ride> getAllWaterRides() throws SQLException;
    String getTimeSlotName(int timeslotId);
}
