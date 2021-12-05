package com.team5.HAPark.user;

import com.team5.HAPark.user.DAO.IUpdateUserInformation;
import com.team5.HAPark.user.DAO.IUserPersistence;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class UpdateUserInformation implements IUpdateUserInformation {

    private UpdateableUser user;

    private EmailPasswordValidation emailPasswordValidation ;

    public UpdateUserInformation(UpdateableUser user) {
        this.user = user;
        emailPasswordValidation = new EmailPasswordValidation(user);
    }

    public boolean updateUserPassword(IUserPersistence userPersistence)
            throws SQLException, NoSuchAlgorithmException, AuthenticationException {

        if (isCurrentUser(userPersistence) && isNewPasswordDifferent()
                && newPasswordMatchesConfirmedPassword()) {

            if (emailPasswordValidation.validatePasswordFormat()) {
                String newPasswordEncrypted = Encryption.encryptPassword(user.getPassword());
                userPersistence.userUpdatedPassword(newPasswordEncrypted,user.getEmail());
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

    private boolean isCurrentUser(IUserPersistence userPersistence) throws SQLException, NoSuchAlgorithmException {
        String email = String.valueOf(SecurityContextHolder.getContext().getAuthentication().getName());
        String currentPasswordEncrypted = userPersistence.getPassword(email);
        String oldPasswordEncrypted = Encryption.encryptPassword(user.getOldPassword());

        boolean emailIsCurrentUser = email.matches(user.getEmail());
        boolean oldPasswordIsCurrentPassword = currentPasswordEncrypted.matches(oldPasswordEncrypted);
        return emailIsCurrentUser && oldPasswordIsCurrentPassword;
    }

    private boolean isNewPasswordDifferent() throws NoSuchAlgorithmException {
        String oldPasswordEncrypted = Encryption.encryptPassword(user.getOldPassword());
        String newPasswordEncrypted = Encryption.encryptPassword(user.getPassword());

        return !oldPasswordEncrypted.matches(newPasswordEncrypted);
    }

    private boolean newPasswordMatchesConfirmedPassword() {
        return user.getPassword().matches(user.getConfirmedPassword());
    }

}
