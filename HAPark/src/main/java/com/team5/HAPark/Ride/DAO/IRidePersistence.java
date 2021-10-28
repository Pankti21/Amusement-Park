package com.team5.HAPark.Ride.DAO;

import java.sql.SQLException;

public interface IRidePersistence {

    void getRide(int id) throws SQLException;

}
