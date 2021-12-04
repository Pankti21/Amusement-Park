package com.team5.HAPark.Ride.Persistence;

import com.team5.HAPark.reserveRide.Persistence.IRideReservePersistence;
import com.team5.HAPark.Ride.Persistence.WaitTime.IWaitTimePersistence;

public interface IRidePersistenceFactory {

    IWaitTimePersistence createWaitTimePersistence();

    IRidePersistence createRidePersistence();

    IRideReservePersistence createRideReservePersistence();

}
