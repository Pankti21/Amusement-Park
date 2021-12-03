package com.team5.HAPark.ride.Controller;

import com.team5.HAPark.ride.Model.*;
import com.team5.HAPark.ride.Persistence.*;
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

    @GetMapping("/reserve")
    public String reserveForm(Model model) throws SQLException {
        model.addAttribute("allrides", rideService.getAllRides());
        model.addAttribute("ride",new RideReserve());
        model.addAttribute("maps",rideService.getAllTimeSlots());
        return "RideForm";
    }

    @PostMapping("/reserved")
    public String submitForm(Model model,@ModelAttribute("ride") RideReserve ride) throws SQLException {
        IRideReservePersistence rideReservePersistence = new RidePersistenceFactory().createRideReservePersistence();
        IRideReserveService rideReserveService=new RideReserveService(rideReservePersistence);
        //https://stackoverflow.com/questions/31159075/how-to-find-out-the-currently-logged-in-user-in-spring-boot
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        String username = currentUser.getName();
        log.info("{}",username);
        log.info("{}ride id {} reserve seats {} timeslot id",ride.getRideId(),ride.getReserveSeats(),ride.getTimeslotId());
        //Reduces availability of rides
        rideReserveService.reserveSeats(ride.getRideId(),ride.getTimeslotId(),ride.getReserveSeats());
        //Saves to database
        rideReserveService.reserve(ride.getRideId(),ride.getTimeslotId(),ride.getReserveSeats());
        model.addAttribute("rideReserved",rideService.getRide(ride.getRideId()));
        model.addAttribute("timeslot",rideService.getTimeSlotName(ride.getTimeslotId()));
        return "RideReserved";
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

    @GetMapping("/reservations")
    public String getAllReservations(Model model) throws SQLException {
        IRideReservePersistence rideReservePersistence = new RidePersistenceFactory().createRideReservePersistence();
        IRideReserveService rideReserveService = new RideReserveService(rideReservePersistence);
        model.addAttribute("reservations",rideReserveService.getReservations());
        return "RideReservations";
    }
}
