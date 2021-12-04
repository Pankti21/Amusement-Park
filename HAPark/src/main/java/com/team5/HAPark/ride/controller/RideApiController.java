package com.team5.HAPark.ride.controller;

import com.team5.HAPark.ride.model.Ride;
import com.team5.HAPark.ride.model.RideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
public class RideApiController {

    @Autowired
    private RideService rideService;

    @RequestMapping("/rides/api")
    public List<Ride> getALLRides() throws SQLException {
        return rideService.getAllRides();
    }

    @RequestMapping("/rides/api/{id}")
    public Ride getRide(@PathVariable int id) throws SQLException {
        return rideService.getRide(id);
    }

    @RequestMapping("/reserve/{id}")
    public Ride reserveRide(@PathVariable int id) throws SQLException {
        //return rideService.reserveRide(id);
        return null;
    }
}

