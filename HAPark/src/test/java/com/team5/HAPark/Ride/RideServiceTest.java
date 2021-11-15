package com.team5.HAPark.Ride;

import com.team5.HAPark.Ride.Model.Ride;
import com.team5.HAPark.Ride.Model.RideService;
import com.team5.HAPark.Ride.Model.TimeSlot;
import com.team5.HAPark.Ride.Persistence.IRidePersistence;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RideServiceTest {
    static RideService rideService;
    public static IRidePersistence ridePersistenceMock;
    static Ride ride1;
    static Ride ride2;
    static List<Ride> rides = new ArrayList<>();

    @BeforeAll
    static void init() throws SQLException {
        HashMap<Integer,Integer> map = new HashMap<>();
        map.put(1,50);
        map.put(2,50);
        map.put(3,50);
        ride1=new Ride(1,"Test Ride","Ground",100, Time.valueOf("00:30:00"),new TimeSlot(map));
        ride2=new Ride(2,"Test Ride2","Water",50, Time.valueOf("00:35:00"),new TimeSlot(map));
        rides.add(ride1);
        rides.add(ride2);
        ridePersistenceMock = Mockito.mock((IRidePersistence.class));
        rideService= new RideService(ridePersistenceMock);
        Mockito.when(ridePersistenceMock.getRide(1)).thenReturn(ride1);
        Mockito.when(ridePersistenceMock.getAllRides()).thenReturn(rides);
    }

    @Test
    void getRide() throws SQLException {
        assertEquals(ride1,rideService.getRide(1));
    }

    @Test
    void getAllRides () throws SQLException {
        assertEquals(2,rideService.getAllRides().size());
    }

    @Test
    void getAllRideNames() throws SQLException {
        List<String> testRideNames = new ArrayList<>();
        testRideNames.add("Test Ride");
        testRideNames.add("Test Ride2");
        assertEquals(testRideNames,rideService.getAllRideNames());
    }
}
