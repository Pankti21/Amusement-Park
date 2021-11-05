package com.team5.HAPark.Ride;

import java.util.ArrayList;
import java.util.List;

public class TimeSlot {
    private List<Integer> id = new ArrayList<>();
    private List<Integer> availability = new ArrayList<>();

    public TimeSlot() {
    }

    public TimeSlot(List<Integer> id, List<Integer> availability) {
        this.id = id;
        this.availability = availability;
    }

    public List<Integer> getId() {
        return id;
    }

    public void setId(List<Integer> id) {
        this.id = id;
    }

    public List<Integer> getAvailability() {
        return availability;
    }

    public void setAvailability(List<Integer> availability) {
        this.availability = availability;
    }

    public void setAvailability() {
    }
}
