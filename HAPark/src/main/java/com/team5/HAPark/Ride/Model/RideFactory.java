package com.team5.HAPark.Ride.Model;

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
