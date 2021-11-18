package com.team5.HAPark.Ride.Controller;

import com.team5.HAPark.Ride.Model.RideReserve;
import com.team5.HAPark.Ride.Model.RideService;
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

    @GetMapping("/rides")
    public String rides(Model model){
        return "RideMainPage";
    }

<<<<<<< HEAD
    @GetMapping("/reserve")
    public String reserveForm(Model model) throws SQLException {
        model.addAttribute("allrides", rideService.getAllRides());
        model.addAttribute("ride",new RideReserve());
        return "RideForm"; //create-project
    }

    @PostMapping("/reserved") //save-project
    public String submitForm(@ModelAttribute("ride") RideReserve ride){
        //Save project to db
        log.info("{}ride id {} reserve seats {} timeslot id",ride.getRideId(),ride.getReserveSeats(),ride.getTimeslotId());
        //System.out.println(ride);
        return "RideReserved"; // result
    }

    @GetMapping("/rides/all")
    public String allrides(Model model) throws SQLException {
        model.addAttribute("allrides", rideService.getAllRides());
        return "rideui";
=======
    @GetMapping("/rides/ground")
    public String groungRides(Model model) throws SQLException {
        model.addAttribute("groundrides",rideService.getAllGroundRides());
        return "GroundRides";
    }

    @GetMapping("/rides/water")
    public String waterRides(Model model) throws SQLException {
        model.addAttribute("waterrides",rideService.getAllWaterRides());
        return "WaterRides";
>>>>>>> feature/rides2
    }
}
