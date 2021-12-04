package com.team5.HAPark.User.DAO;

import com.team5.HAPark.Database.mysql.MySQLDatabase;

public class UserPersistenceFactory implements IUserPersistenceFactory {

    private static IUserPersistence userPersistence;

    @Override
    public IUserPersistence createUserPersistence() {
        if (userPersistence == null) {
            userPersistence = new MySQLUserPersistence(MySQLDatabase.getInstance());
        }
        return userPersistence;
    }
}
