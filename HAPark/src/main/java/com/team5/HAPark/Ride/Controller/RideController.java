package com.team5.HAPark.Ride.Controller;

import com.team5.HAPark.ReserveRide.Model.IRideReserveService;
import com.team5.HAPark.ReserveRide.Model.RideReserve;
import com.team5.HAPark.ReserveRide.Model.RideReserveService;
import com.team5.HAPark.Ride.Model.*;
import com.team5.HAPark.ReserveRide.Persistence.IRideReservePersistence;
import com.team5.HAPark.Ride.Persistence.RidePersistence;
import com.team5.HAPark.ReserveRide.Persistence.RideReservePersistence;
import com.team5.HAPark.database.mysql.MySQLDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    WaitTimeService waitTimeService=new WaitTimeService();

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
        RidePersistence ridePersistence = new RidePersistence(MySQLDatabase.getInstance());
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
