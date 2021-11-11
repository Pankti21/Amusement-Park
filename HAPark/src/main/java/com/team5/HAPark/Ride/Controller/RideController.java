package com.team5.HAPark.Ride.Controller;

import com.team5.HAPark.Ride.Model.RideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.sql.SQLException;
import java.util.Arrays;

@org.springframework.stereotype.Controller
public class RideController {

    @Autowired
    private RideService rideService;

    @GetMapping("/rides")
    public String rides(Model model){
        return "RideMainPage";
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
