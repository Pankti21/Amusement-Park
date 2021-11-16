package com.team5.HAPark.Ride.Model;

import java.util.HashMap;

public class TimeSlot {
    //Map key is timeslot id and value is availability
    HashMap<Integer,Integer> map= new HashMap<>();

    public TimeSlot(HashMap<Integer, Integer> map) {
        this.map = map;
    }

    public TimeSlot() {
    }

}
