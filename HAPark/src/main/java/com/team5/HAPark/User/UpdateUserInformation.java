package com.team5.HAPark.User;

import com.team5.HAPark.User.DAO.IUserPersistence;
import com.team5.HAPark.User.DAO.MySQLUserPersistence;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class UpdateUserInformation {

    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public EmailPasswordValidation getEmailPasswordValidation() {
        return emailPasswordValidation;
    }

    public void setEmailPasswordValidation(EmailPasswordValidation emailPasswordValidation) {
        this.emailPasswordValidation = emailPasswordValidation;
    }

    private EmailPasswordValidation emailPasswordValidation ;

    public UpdateUserInformation(User user) {
        this.user = user;
        emailPasswordValidation = new EmailPasswordValidation(user);
    }

    public boolean updateUserPassword(IUserPersistence userPersistence,
                                      String oldPassword,String confirmedPassword, String reconfirmPassword)
            throws SQLException, NoSuchAlgorithmException, AuthenticationException {

        String email = String.valueOf(SecurityContextHolder.getContext().getAuthentication().getName());
        String currentPassword = userPersistence.getPassword(email);
        oldPassword = Encryption.encryptPassword(oldPassword);
        System.out.println(confirmedPassword);
        System.out.println(reconfirmPassword);

        //Validating the old password is not same as new password and new password is as same confirmed password and old password
        //is same as the current password
        if ((reconfirmPassword.matches(confirmedPassword) )&& (!(currentPassword.matches(confirmedPassword))) &&
                (oldPassword.matches(currentPassword))) {

            if (emailPasswordValidation.validatePasswordFormat()) {
                userPersistence.userUpdatedPassword(reconfirmPassword,email);
                return true;
            }
            else {
                System.out.println("Password format is not correct");
            }
        }
        else {
            System.out.println("Password don't match");
            throw new NoSuchAlgorithmException("Password don't match");
        }
        return false;
    }

}
