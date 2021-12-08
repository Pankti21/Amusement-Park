package com.team5.HAPark.waitTime;

import com.team5.HAPark.reserveRide.persistence.IRideReservePersistence;
import com.team5.HAPark.ride.persistence.IRidePersistence;
import com.team5.HAPark.ride.persistence.IRidePersistenceFactory;
import com.team5.HAPark.ride.persistence.RidePersistenceFactory;
import com.team5.HAPark.waitTime.persistence.IWaitTimePersistence;
import com.team5.HAPark.waitTime.persistence.IWaitTimePersistenceFactory;
import com.team5.HAPark.waitTime.persistence.WaitTimePersistenceFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class WaitTimePersistenceFactoryTest {
    private IWaitTimePersistence waitTimePersistence1;
    private IWaitTimePersistence waitTimePersistence2;

    private IRidePersistence ridePersistence1;
    private IRidePersistence ridePersistence2;

    private IRideReservePersistence rideReservePersistence1;
    private IRideReservePersistence rideReservePersistence2;

    @BeforeEach
    void setUp() {
        IWaitTimePersistenceFactory waitTimePersistenceFactory1 = new WaitTimePersistenceFactory();
        IWaitTimePersistenceFactory waitTimePersistenceFactory2 = new WaitTimePersistenceFactory();

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
