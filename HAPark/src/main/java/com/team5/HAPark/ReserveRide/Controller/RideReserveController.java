package com.team5.HAPark.ReserveRide.Controller;

import com.team5.HAPark.ReserveRide.Model.IRideReserveService;
import com.team5.HAPark.ReserveRide.Model.RideReserve;
import com.team5.HAPark.ReserveRide.Model.RideReserveService;
import com.team5.HAPark.ReserveRide.Persistence.IRideReservePersistence;
import com.team5.HAPark.ReserveRide.Persistence.RideReservePersistence;
import com.team5.HAPark.Ride.Model.RideService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.sql.SQLException;

@Slf4j
@Controller
public class RideReserveController {
    @Autowired
    private RideService rideService;

    @GetMapping("/reserve")
    public String reserveForm(Model model) throws SQLException {
        model.addAttribute("allrides", rideService.getAllRides());
        model.addAttribute("ride",new RideReserve());
        model.addAttribute("maps",rideService.getAllTimeSlots());
        return "RideForm";
    }

    @PostMapping("/reserved")
    public String submitForm(Model model,@ModelAttribute("ride") RideReserve ride) throws SQLException {
        IRideReservePersistence rideReservePersistence=new RideReservePersistence();
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

    @GetMapping("/reservations")
    public String getAllReservations(Model model) throws SQLException {
        IRideReservePersistence rideReservePersistence= new RideReservePersistence();
        IRideReserveService rideReserveService=new RideReserveService(rideReservePersistence);
        model.addAttribute("reservations",rideReserveService.getReservations());
        return "RideReservations";
    }
}
