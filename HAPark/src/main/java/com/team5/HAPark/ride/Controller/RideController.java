package com.team5.HAPark.ride.Controller;

import com.team5.HAPark.reserveRide.Model.RideReserveService;
import com.team5.HAPark.ride.Model.*;
import com.team5.HAPark.ride.Persistence.IRidePersistence;
import com.team5.HAPark.ride.Persistence.RidePersistenceFactory;
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

    @Autowired
    WaitTimeService waitTimeService = new WaitTimeService();

    @GetMapping("/rides")
    public String rides(Model model) throws SQLException {
        RideReserveService rideReserveService=new RideReserveService();
        return "RideMainPage";
    }

    @GetMapping("/rides/waittime")
    public String waitTime(Model model) throws SQLException {
        model.addAttribute("wt",waitTimeService.calculateWaitTime(1).getWaitTime());
        model.addAttribute("wts",waitTimeService.getWaitTimes());
        return "WaitTime";
    }

    @GetMapping("/rides/all")
    public String allrides(Model model) throws SQLException {
        model.addAttribute("allrides", rideService.getAllRides());
        model.addAttribute("maps",rideService.getAllTimeSlots());
        return "rideui";
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
