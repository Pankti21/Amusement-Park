package com.team5.HAPark.ride.persistence;

import com.team5.HAPark.reserveRide.persistence.IRideReservePersistence;
import com.team5.HAPark.waitTime.persistence.IWaitTimePersistence;

public interface IRidePersistenceFactory {

    IWaitTimePersistence createWaitTimePersistence();

    IRidePersistence createRidePersistence();

    IRideReservePersistence createRideReservePersistence();

}
