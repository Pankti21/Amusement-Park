package com.team5.HAPark.User;

<<<<<<< HEAD:HAPark/src/main/java/com/team5/HAPark/userAuthentication/Login.java
import com.team5.HAPark.database.mysql.IUserPersistence;
=======
import com.team5.HAPark.User.DAO.IUserPersistence;
>>>>>>> 778932d59e2a554be39ea78c21e0641f84831d5d:HAPark/src/main/java/com/team5/HAPark/User/Login.java

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
                Encryption encryption = new Encryption();
                String enteredEncryptedPassword = encryption.encryptPassword(user.getPassword());
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
