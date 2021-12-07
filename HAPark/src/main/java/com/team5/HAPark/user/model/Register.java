package com.team5.HAPark.user.model;

import com.team5.HAPark.user.persistence.IUserPersistence;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class Register {

    private final RegisterUser user;
    private final IEmailPasswordValidation emailPasswordValidation ;

    public Register(RegisterUser user) {
        this.user = user;
        emailPasswordValidation = new EmailPasswordValidation(this.user);
    }

    public boolean register(IUserPersistence userPersistence) {
        if (validateUserInfo()) {
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

    private boolean validateUserInfo() {
        if (fieldIsPresent(user.getPassword(), user.getEmail(), user.getFirstName(), user.getLastName(), user.getConfirmedPassword())) {
            if (emailPasswordValidation.validateEmailFormat() && emailPasswordValidation.validatePasswordFormat()) {
                if (user.getPassword().matches(user.getConfirmedPassword())) {
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