package com.team5.HAPark.ride.persistence;

import com.team5.HAPark.database.mysql.MySQLDatabase;
import com.team5.HAPark.reserveRide.persistence.IRideReservePersistence;
import com.team5.HAPark.reserveRide.persistence.RideReservePersistence;
import com.team5.HAPark.waitTime.persistence.IWaitTimePersistence;
import com.team5.HAPark.waitTime.persistence.WaitTimePersistence;

public class RidePersistenceFactory implements IRidePersistenceFactory{

    private static IRidePersistence ridePersistence;

    @Override
    public IRidePersistence createRidePersistence() {
        if (ridePersistence == null){
            ridePersistence = new RidePersistence(MySQLDatabase.getInstance());
        }
        return ridePersistence;
    }


}
