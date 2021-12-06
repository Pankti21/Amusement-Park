package com.team5.HAPark.ride.controller;

import com.team5.HAPark.reserveRide.model.RideReserveService;
import com.team5.HAPark.ride.model.*;
import com.team5.HAPark.ride.persistence.IRidePersistence;
import com.team5.HAPark.ride.persistence.RidePersistenceFactory;
import com.team5.HAPark.waitTime.model.WaitTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;

import java.sql.SQLException;

@org.springframework.stereotype.Controller
@Slf4j
public class RideController {

    @Autowired
    private RideService rideService;

    @GetMapping("/rides/all")
    public String allrides(Model model) throws SQLException {
        model.addAttribute("allrides", rideService.getAllRides());
        model.addAttribute("maps",rideService.getAllTimeSlots());
        return "Ride";
    }

    public void allTimeSlots(Model model) throws SQLException {
        IRidePersistence ridePersistence = new RidePersistenceFactory().createRidePersistence();
        model.addAttribute("alltimeslots", ridePersistence.getAllTimeSlots());
    }

    @GetMapping("/rides/ground")
    public String groungRides(Model model) throws SQLException {
        model.addAttribute("groundrides",rideService.getAllGroundRides());
        return "GroundRides";
    }

    @GetMapping("/rides/water")
    public String waterRides(Model model) throws SQLException {
        model.addAttribute("waterrides",rideService.getAllWaterRides());
        return "WaterRides";
    }
}
