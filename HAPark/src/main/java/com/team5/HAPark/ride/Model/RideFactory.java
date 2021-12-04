package com.team5.HAPark.ride.Model;

public class RideFactory {
    public Ride getRide(String ride){
        if(ride==null){
            return null;
        }
        if(ride.equalsIgnoreCase("RIDE")){
            return new Ride();
        }
        return null;
    }
}
