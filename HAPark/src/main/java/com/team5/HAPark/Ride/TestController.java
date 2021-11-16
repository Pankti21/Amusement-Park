package com.team5.HAPark.Ride;

import com.team5.HAPark.Ride.Model.Ride;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Controller
public class TestController {
    @GetMapping(value = "/rides/test")
    public String test(Model model){
        List<Ride> r=new ArrayList<>();
        Ride ride=new Ride(1,"water");
        r.add(ride);
        ride=new Ride(2,"ground");
        r.add(ride);
        model.addAttribute("testride",r);
        return "rideui1";
    }
}
