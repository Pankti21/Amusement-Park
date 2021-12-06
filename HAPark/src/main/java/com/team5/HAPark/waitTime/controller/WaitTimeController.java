package com.team5.HAPark.waitTime.controller;

import com.team5.HAPark.reserveRide.model.RideReserveService;
import com.team5.HAPark.ride.model.RideService;
import com.team5.HAPark.waitTime.model.WaitTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.sql.SQLException;

@Controller
public class WaitTimeController {

    @Autowired
    private RideService rideService;

    @Autowired
    WaitTimeService waitTimeService = new WaitTimeService();

    @GetMapping("/rides/waittime")
    public String waitTime(Model model) throws SQLException {
        model.addAttribute("allrides", rideService.getAllRides());
        model.addAttribute("wts",waitTimeService.getWaitTimes());
        return "WaitTime";
    }
}
