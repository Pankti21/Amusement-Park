package com.team5.HAPark.ride.persistence;

import com.team5.HAPark.reserveRide.persistence.IRideReservePersistence;
import com.team5.HAPark.waitTime.persistence.IWaitTimePersistence;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RidePersistenceFactoryTest {
    private IRidePersistence ridePersistence1;
    private IRidePersistence ridePersistence2;


    @BeforeEach
    void setUp() {
        IRidePersistenceFactory ridePersistenceFactory1 = new RidePersistenceFactory();
        IRidePersistenceFactory ridePersistenceFactory2 = new RidePersistenceFactory();

        ridePersistence1 = ridePersistenceFactory1.createRidePersistence();
        ridePersistence2 = ridePersistenceFactory2.createRidePersistence();

    }

    @Test
    void createRidePersistenceNotNull() {
        assertNotNull(ridePersistence1);
    }

    @Test
    void createRidePersistenceSameInstance() {
        assertEquals(ridePersistence1,ridePersistence2);
    }
}