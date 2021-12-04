package com.team5.HAPark.User;

import com.team5.HAPark.User.DAO.IUserPersistence;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class Register {

    private final User user;
    private final EmailPasswordValidation emailPasswordValidation ;

    public Register(User user) {
        this.user = user;
        emailPasswordValidation = new EmailPasswordValidation(this.user);
    }

    public boolean register(IUserPersistence userPersistence, String confirmedPassword) {

        if (user.getPassword()!=null && !user.getPassword().isEmpty()
                && user.getEmail()!=null && !user.getEmail().isEmpty()
                && user.getFirstName()!=null && !user.getFirstName().isEmpty()
                && user.getLastName()!=null && !user.getLastName().isEmpty()
                && confirmedPassword!=null){

            if (emailPasswordValidation.validateEmailFormat() && emailPasswordValidation.validatePasswordFormat()) {

                if (user.getPassword().matches(confirmedPassword)) {

                    try {
                        if (!userPersistence.doesUserExist(user.getEmail())) {
                            userPersistence.saveUser(user.getEmail(), user.getFirstName(),
                                    user.getLastName(),Encryption.encryptPassword(user.getPassword()));
                            return true;
                        }
                    } catch (SQLException | NoSuchAlgorithmException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return false;
    }


}