package com.team5.HAPark.Ride.Model;

import com.team5.HAPark.Ride.Persistence.IRidePersistence;

import java.sql.SQLException;
import java.util.List;

public interface IRideService {
    IRidePersistence ridePersistence = null;
    public List<Ride> getAllRides() throws SQLException;
    public Ride getRide(int id) throws SQLException;
    //public Ride reserveRide(int id) throws SQLException;

}
