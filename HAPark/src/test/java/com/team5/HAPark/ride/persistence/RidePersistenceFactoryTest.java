package com.team5.HAPark.ride.persistence;

import com.team5.HAPark.reserveRide.persistence.IRideReservePersistence;
import com.team5.HAPark.waitTime.persistence.IWaitTimePersistence;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RidePersistenceFactoryTest {

    private IWaitTimePersistence waitTimePersistence1;
    private IWaitTimePersistence waitTimePersistence2;

    private IRidePersistence ridePersistence1;
    private IRidePersistence ridePersistence2;

    private IRideReservePersistence rideReservePersistence1;
    private IRideReservePersistence rideReservePersistence2;

    @BeforeEach
    void setUp() {
        IRidePersistenceFactory ridePersistenceFactory1 = new RidePersistenceFactory();
        IRidePersistenceFactory ridePersistenceFactory2 = new RidePersistenceFactory();

        waitTimePersistence1 = ridePersistenceFactory1.createWaitTimePersistence();
        waitTimePersistence2 = ridePersistenceFactory2.createWaitTimePersistence();

        ridePersistence1 = ridePersistenceFactory1.createRidePersistence();
        ridePersistence2 = ridePersistenceFactory2.createRidePersistence();

    }

    @Test
    void createWaitTimePersistenceNotNull() {
        assertNotNull(waitTimePersistence1);
    }

    @Test
    void createWaitTimePersistenceSameInstance() {
        assertEquals(waitTimePersistence1,waitTimePersistence2);
    }

    @Test
    void createRidePersistenceNotNull() {
        assertNotNull(ridePersistence1);
    }

    @Test
    void createRidePersistenceSameInstance() {
        assertEquals(ridePersistence1,ridePersistence2);
    }

    @Test
    void createRideReservePersistenceNotNull() {
        assertNotNull(rideReservePersistence1);
    }

    @Test
    void createRideReservePersistenceSameInstance() {
        assertEquals(rideReservePersistence1,rideReservePersistence2);
    }
}