package com.team5.HAPark.User;

import com.team5.HAPark.User.DAO.IUserPersistence;
import org.springframework.security.core.context.SecurityContextHolder;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class UpdateUserInformation {

    private User user;
    private EmailPasswordValidation emailPasswordValidation;

    public UpdateUserInformation(User user) {
        this.user = user;
    }


    /*
        public boolean updateUserEmailInfo(IUserPersistence userPersistence, String confirmedEmail, String uiPassword ) throws SQLException {
            String oldEmail = SecurityContextHolder.getContext().getAuthentication().getName();
            String password = String.valueOf(SecurityContextHolder.getContext().getAuthentication().getCredentials());
            if ((uiPassword.equals(password)  )) {

                if (emailPasswordValidation.validateEmailFormat()) {
                    userPersistence.updateUserEmailInfo(confirmedEmail);
                }

            }
            return false;
            }*/
    public boolean updateUserPassword(IUserPersistence userPersistence, String confirmedPassword, String reconfirmPassword) throws SQLException, NoSuchAlgorithmException {

        String email = String.valueOf(SecurityContextHolder.getContext().getAuthentication().getName());
        String currentPassword = userPersistence.getPassword(email);
        confirmedPassword = Encryption.encryptPassword(confirmedPassword);

        if ((reconfirmPassword.matches(confirmedPassword) )&& (!(currentPassword.matches(confirmedPassword)))) {

            if (emailPasswordValidation.validatePasswordFormat(userPersistence,user.getPassword())) {
                userPersistence.userUpdatedPassword(confirmedPassword,email);
            }

        }
        return false;
    }

}
