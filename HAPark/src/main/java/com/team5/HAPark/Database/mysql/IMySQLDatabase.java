package com.team5.HAPark.Database.mysql;

import com.team5.HAPark.Database.IDataBase;

import java.sql.Connection;
import java.sql.SQLException;

public interface IMySQLDatabase extends IDataBase {
    Connection getConnection() throws SQLException;
}
