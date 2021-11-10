package com.team5.HAPark.Ride;

import com.team5.HAPark.Ride.DAO.RidePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
public class RideController {

    @Autowired
    private RideService rideService;

    @RequestMapping("/rides")
    public List<Ride> getALLRides() throws SQLException {
        return rideService.getAllRides();
    }

    @RequestMapping("/rides/{id}")
    public Ride getRide(@PathVariable int id) throws SQLException {
        return rideService.getRide(id);
    }

    @RequestMapping("/reserve/{id}")
    public Ride reserveRide(@PathVariable int id) throws SQLException {
        //return rideService.reserveRide(id);
        return null;
    }
    @RequestMapping(method = RequestMethod.POST, value = "/rides")
    public void addRide(@RequestBody Ride ride){
        rideService.addRide(ride);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/rides/{id}")
    public void updateRide(@RequestBody Ride ride,@PathVariable int id){
        rideService.updateRide(ride,id);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/rides/{id}")
    public void deleteRide(@PathVariable int id){
        rideService.deleteRide(id);
    }

    }

