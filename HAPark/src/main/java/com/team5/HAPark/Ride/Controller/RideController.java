package com.team5.HAPark.Ride.Controller;

import com.team5.HAPark.Ride.Model.Ride;
import com.team5.HAPark.Ride.Model.RideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/reserve")
    public String reserveForm(Model model){
        Ride ride=new Ride();
        model.addAttribute("ride",ride);
        return "RideForm";
    }

    @PostMapping("/reserve")
    public String submitForm(@ModelAttribute("ride") Ride ride){
        System.out.println(ride);
        return "RideReserved";
    }

    @GetMapping("/rides/all")
    public String allrides(Model model) throws SQLException {
        model.addAttribute("allrides", rideService.getAllRides());
        return "rideui";
    }
}
