package com.team5.HAPark.Ride.Controller;

import com.team5.HAPark.Ride.Model.RideReserve;
import com.team5.HAPark.Ride.Model.RideService;
import com.team5.HAPark.Ride.Persistence.RidePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;

import java.sql.SQLException;
import java.util.List;

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
    public String reserveForm(Model model) throws SQLException {
        model.addAttribute("allrides", rideService.getAllRides());
        model.addAttribute("ride",new RideReserve());
        model.addAttribute("maps",rideService.getAllTimeSlots());
        return "RideForm"; //create-project
    }

    @PostMapping("/reserved") //save-project
    public String submitForm(@ModelAttribute("ride") RideReserve ride) throws SQLException {
        //Save project to db
        //https://stackoverflow.com/questions/31159075/how-to-find-out-the-currently-logged-in-user-in-spring-boot
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        String username = currentUser.getName();
        log.info("{}",username);
        log.info("{}ride id {} reserve seats {} timeslot id",ride.getRideId(),ride.getReserveSeats(),ride.getTimeslotId());
        rideService.reserveSeats(ride.getRideId(),ride.getTimeslotId(),ride.getReserveSeats());
        return "RideReserved"; // result
    }

    @GetMapping("/rides/all")
    public String allrides(Model model) throws SQLException {
        model.addAttribute("allrides", rideService.getAllRides());
        RidePersistence ridePersistence = new RidePersistence();
        //List<Integer> keys=List.of(1,2,3);
        model.addAttribute("maps",ridePersistence.getAllTimeSlots());
        //model.addAttribute("alltimeslots", ridePersistence.getAllTimeSlots());
        return "rideui";
    }

    public void allTimeSlots(Model model) throws SQLException {
        RidePersistence ridePersistence = new RidePersistence();
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
