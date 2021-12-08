package com.team5.HAPark.ride.persistence;

import com.team5.HAPark.reserveRide.persistence.IRideReservePersistence;
import com.team5.HAPark.waitTime.persistence.IWaitTimePersistence;

public interface IRidePersistenceFactory {

    IRidePersistence createRidePersistence();

}
