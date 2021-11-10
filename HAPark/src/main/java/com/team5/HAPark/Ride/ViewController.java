package com.team5.HAPark.Ride;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.sql.SQLException;
import java.util.Arrays;

@Controller
public class ViewController {

    @Autowired
    private RideService rideService;

    @GetMapping("/rides")
    public String allrides(Model model) throws SQLException {
        model.addAttribute("allrides", Arrays.asList(rideService.getAllRideNames()));
        return "rideui";
    }
}
