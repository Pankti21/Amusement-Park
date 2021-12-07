package com.team5.HAPark.user.model;

import com.team5.HAPark.user.persistence.IUserPersistence;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class Register {

    private final User user;
    private final IEmailPasswordValidation emailPasswordValidation ;

    public Register(User user) {
        this.user = user;
        emailPasswordValidation = new EmailPasswordValidation(this.user);
    }

    public boolean register(IUserPersistence userPersistence, String confirmedPassword) {
        if (validateUserInfo(confirmedPassword)) {
            try {
                if (!userPersistence.doesUserExist(user.getEmail())) {
                    userPersistence.saveUser(user.getEmail(), user.getFirstName(),
                            user.getLastName(), Encryption.encryptPassword(user.getPassword()));
                    return true;
                }
            } catch (SQLException | NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    private boolean validateUserInfo(String confirmedPassword) {
        if (fieldIsPresent(user.getPassword(), user.getEmail(), user.getFirstName(), user.getLastName(), confirmedPassword)) {
            if (emailPasswordValidation.validateEmailFormat() && emailPasswordValidation.validatePasswordFormat()) {
                if (user.getPassword().matches(confirmedPassword)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean fieldIsPresent(String ... fields) {
        for (String field : fields){
            if (field == null || field.isEmpty()) {
                return false;
            }
        }
        return true;
    }
}