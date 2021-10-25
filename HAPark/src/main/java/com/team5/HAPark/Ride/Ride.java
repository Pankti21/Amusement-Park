package com.team5.HAPark.Ride;

import java.sql.Time;
import java.time.LocalTime;

public class Ride {
    private int id;
    private String name;
    private String type;
    private int maxOccupancy;
    private LocalTime duration;


    public Ride() {
    }

    public Ride(int id, String name, String type, int maxOccupancy, LocalTime duration) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.maxOccupancy = maxOccupancy;
        this.duration = duration;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getMaxOccupancy() {
        return maxOccupancy;
    }

    public void setMaxOccupancy(int maxOccupancy) {
        this.maxOccupancy = maxOccupancy;
    }

    public LocalTime getDuration() {
        return duration;
    }

    public void setDuration(LocalTime duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "Ride{" +
                "duration=" + duration +
                '}';
    }
}
