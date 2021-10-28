package com.team5.HAPark.Ride.DAO;

import java.sql.SQLException;

public interface IRidePersistence {

    String getRide(int id) throws SQLException;

}
