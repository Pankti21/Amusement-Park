package com.team5.HAPark.Database;

import com.team5.HAPark.Database.mysql.MySQLDatabase;

public class DatabaseFactory implements IDatabaseFactory{

    private static IDataBase dataBase;

    @Override
    public IDataBase createDatabase() {
        if (dataBase == null) {
            dataBase = MySQLDatabase.getInstance();
        }
        return dataBase;
    }
}
