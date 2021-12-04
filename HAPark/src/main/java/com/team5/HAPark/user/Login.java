package com.team5.HAPark.user;
import com.team5.HAPark.user.DAO.IUserPersistence;

import java.security.NoSuchAlgorithmException;
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
                String enteredEncryptedPassword = Encryption.encryptPassword(user.getPassword());
                String savedPassword = userPersistence.getPassword(user.getEmail());
                if (enteredEncryptedPassword.equals(savedPassword)) {
                    user = userPersistence.loadUser(user.getEmail());
                    loggedIn = true;
                }
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return loggedIn;
    }
}
