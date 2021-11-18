package com.team5.HAPark.Ride.Controller;

import com.team5.HAPark.Ride.Model.Ride;
import com.team5.HAPark.Ride.Model.RideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Arrays;

@org.springframework.stereotype.Controller
@Slf4j
public class RideController {

    @Autowired
    private RideService rideService;

    @GetMapping("/rides")
    public String rides(Model model){
        return "RideMainPage";
    }

    @GetMapping("/reserve")
    public String reserveForm(Model model){
        model.addAttribute("ride",new Ride());
        return "RideForm"; //create-project
    }

    @PostMapping("/reserved") //save-project
    public String submitForm(@ModelAttribute Ride ride){
        //Save project to db
        log.info("{}ride {}",ride.getId(),ride.getName());
        System.out.println(ride);
        return "RideReserved"; // result
    }

    @GetMapping("/rides/all")
    public String allrides(Model model) throws SQLException {
        model.addAttribute("allrides", rideService.getAllRides());
        return "rideui";
    }
}
