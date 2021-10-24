package com.team5.HAPark.userAuthentication;

import database.IUserPersistence;
import java.sql.SQLException;

public class Login {

    private User user;

    public Login(User user) {
        this.user = user;
    }

    public boolean login(IUserPersistence userPersistence) {

        boolean loggedIn = false;

        try {

            if (userPersistence.doesUserExist(user.getEmail())) {
                String password = userPersistence.getPassword(user.getEmail());
                if (user.getPassword().equals(password)) {
                    user = userPersistence.loadUser(user.getEmail());
                    loggedIn = true;
                }
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }

        return loggedIn;
    }
}
