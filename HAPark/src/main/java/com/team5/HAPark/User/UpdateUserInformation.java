package com.team5.HAPark.User;

import com.team5.HAPark.User.DAO.IUserPersistence;
import com.team5.HAPark.User.DAO.MySQLUserPersistence;
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
    public boolean updateUserPassword(IUserPersistence userPersistence, String oldPassword,String confirmedPassword, String reconfirmPassword) throws SQLException, NoSuchAlgorithmException {

        String email = String.valueOf(SecurityContextHolder.getContext().getAuthentication().getName());
        String currentPassword = userPersistence.getPassword(email);
        confirmedPassword = Encryption.encryptPassword(confirmedPassword);

        //Validating the old password is not same as new password and new password is as same confirmed password and old password
        //is same as the current password
        if ((reconfirmPassword.matches(confirmedPassword) )&& (!(currentPassword.matches(confirmedPassword))) &&
                oldPassword.matches(currentPassword)) {

            if (emailPasswordValidation.validatePasswordFormat()) {
                userPersistence.userUpdatedPassword(confirmedPassword,email);
            }

        }
        return false;
    }

}
