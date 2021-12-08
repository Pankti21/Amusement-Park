package com.team5.HAPark.ride.persistence;

import com.team5.HAPark.reserveRide.persistence.IRideReservePersistence;
import com.team5.HAPark.reserveRide.persistence.IRideReservePersistenceFactory;
import com.team5.HAPark.reserveRide.persistence.RideReservePersistenceFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class RideReservePersistenceFactoryTest {

    private IRideReservePersistence rideReservePersistence1;
    private IRideReservePersistence rideReservePersistence2;

    @BeforeEach
    void setUp() {
        IRideReservePersistenceFactory rideReservePersistenceFactory1 = new RideReservePersistenceFactory();
        IRideReservePersistenceFactory rideReservePersistenceFactory2 = new RideReservePersistenceFactory();

        rideReservePersistence1 = rideReservePersistenceFactory1.createRideReservePersistence();
        rideReservePersistence2 = rideReservePersistenceFactory2.createRideReservePersistence();
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
