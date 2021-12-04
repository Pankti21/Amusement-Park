package com.team5.HAPark.ride.Persistence;

import com.team5.HAPark.database.mysql.MySQLDatabase;
import com.team5.HAPark.reserveRide.Persistence.IRideReservePersistence;
import com.team5.HAPark.reserveRide.Persistence.RideReservePersistence;
import com.team5.HAPark.ride.Persistence.WaitTime.IWaitTimePersistence;
import com.team5.HAPark.ride.Persistence.WaitTime.WaitTimePersistence;

public class RidePersistenceFactory implements IRidePersistenceFactory{

    private static IWaitTimePersistence waitTimePersistence;
    private static IRidePersistence ridePersistence;
    private static IRideReservePersistence rideReservePersistence;

    @Override
    public IWaitTimePersistence createWaitTimePersistence() {
        if (waitTimePersistence == null){
            waitTimePersistence = new WaitTimePersistence(MySQLDatabase.getInstance());
        }
        return waitTimePersistence;
    }

    @Override
    public IRidePersistence createRidePersistence() {
        if (ridePersistence == null){
            ridePersistence = new RidePersistence(MySQLDatabase.getInstance());
        }
        return ridePersistence;
    }

    @Override
    public IRideReservePersistence createRideReservePersistence() {
        if (rideReservePersistence == null){
            rideReservePersistence = new RideReservePersistence(MySQLDatabase.getInstance());
        }
        return rideReservePersistence;
    }

}
