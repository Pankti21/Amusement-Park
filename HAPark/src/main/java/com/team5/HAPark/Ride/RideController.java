package com.team5.HAPark.Ride;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RideController {

    @Autowired
    private RideService rideService;

    @RequestMapping("/rides")
    public List<Ride> getALLRides(){
        return rideService.getAllRides();
    }

    @RequestMapping("/rides/{id}")
    public Ride getRide(@PathVariable int id){
        return rideService.getRide(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/rides")
    public void addRide(@RequestBody Ride ride){
        rideService.addRide(ride);
    }
}
