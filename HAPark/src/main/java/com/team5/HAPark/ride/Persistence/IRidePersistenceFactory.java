package com.team5.HAPark.ride.Persistence;

import com.team5.HAPark.ride.Persistence.WaitTime.IWaitTimePersistence;

public interface IRidePersistenceFactory {

    IWaitTimePersistence createWaitTimePersistence();

    IRidePersistence createRidePersistence();

    IRideReservePersistence createRideReservePersistence();

}
