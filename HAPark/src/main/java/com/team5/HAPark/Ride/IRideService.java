package com.team5.HAPark.Ride;

import java.sql.SQLException;
import java.util.List;

public interface IRideService {
    public List<Ride> getAllRides() throws SQLException;
    public Ride getRide(int id) throws SQLException;
    public Ride reserveRide(int id) throws SQLException;
}
