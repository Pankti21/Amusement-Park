package com.team5.HAPark.User;

import com.team5.HAPark.User.DAO.IUserPersistence;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;

import java.security.NoSuchAlgorithmException;
import org.junit.jupiter.api.Test;
import java.sql.SQLException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UpdateUserInformationTest {

    private static UpdateUserInformation updateUserInformation;
    private static User user;
    private static IUserPersistence userPersistenceMock;

    @BeforeEach
    void init() {
        user = new User();
        user.setConfirmedPassword("NewPassword@123");
        user.setReconfirmedPassword("NewPassword@123");
        user.setEmail("test@gmail.com");
        user.setPassword("password");
        updateUserInformation = new UpdateUserInformation(user);
        userPersistenceMock = Mockito.mock(IUserPersistence.class);
    }
    @Test
    @WithMockUser(username = "test@gmail.com")
    public void validatingOldAndCurrentPasswordAreSame() throws SQLException, NoSuchAlgorithmException {
        String oldPassword = Encryption.encryptPassword("password");
        when(userPersistenceMock.getPassword("test@gmail.com")).thenReturn(oldPassword);
        assertFalse( updateUserInformation.updateUserPassword
                        (userPersistenceMock, user.getPassword(),user.getConfirmedPassword(), user.getReconfirmedPassword()));
    }

   /* @Test
    @WithMockUser(username = "test@gmail.com")
    public void validatingNewAndConfirmedPasswordAreSame() throws SQLException, NoSuchAlgorithmException {
        user.setReconfirmedPassword("ConfPassword@123");
        String oldPassword = Encryption.encryptPassword("password");
        when(userPersistenceMock.getPassword("test@gmail.com")).thenReturn(oldPassword);
        assertEquals("Password don't match",
                updateUserInformation.updateUserPassword(userPersistenceMock, user.getPassword(),
                        user.getConfirmedPassword(), user.getReconfirmedPassword()));
    }*/

    @Test
    @WithMockUser(username = "test@gmail.com")
    public void validatingOldAndNewPasswordAreNotSame() throws SQLException, NoSuchAlgorithmException {
        user.setConfirmedPassword("password");
        user.setReconfirmedPassword("password");
        String oldPassword = Encryption.encryptPassword("password");
        when(userPersistenceMock.getPassword("test@gmail.com")).thenReturn(oldPassword);
        assertFalse("Password don't match",
                updateUserInformation.updateUserPassword(userPersistenceMock, user.getPassword(),
                        user.getConfirmedPassword(), user.getReconfirmedPassword()));
    }

    @Test
    @WithMockUser(username = "test@gmail.com")
    public void validatingThePasswordUpdatedIsInCorrectFormat() throws SQLException, NoSuchAlgorithmException {
        String oldPassword = Encryption.encryptPassword("password");
        user.setConfirmedPassword("NewPassword@123");
        user.setReconfirmedPassword("NewPassword@123");
        when(userPersistenceMock.getPassword("test@gmail.com")).thenReturn(oldPassword);
        assertFalse( updateUserInformation.updateUserPassword
                (userPersistenceMock, user.getPassword(),user.getConfirmedPassword(), user.getReconfirmedPassword()));
    }


    @Test
    @WithMockUser(username = "test@gmail.com")
    public void validatingUpdatedPasswordHasAtleastOneDigit() throws SQLException, NoSuchAlgorithmException {
        String oldPassword = Encryption.encryptPassword("password");
        user.setConfirmedPassword("NewPassword@");
        user.setReconfirmedPassword("NewPassword@");
        when(userPersistenceMock.getPassword("test@gmail.com")).thenReturn(oldPassword);
        assertFalse("Password format is not correct", updateUserInformation.updateUserPassword
                (userPersistenceMock, user.getPassword(),user.getConfirmedPassword(), user.getReconfirmedPassword()));
    }
    @Test
    @WithMockUser(username = "test@gmail.com")
    public void validatingUpdatedPasswordHasAtleastOneSpecialCharacter() throws SQLException, NoSuchAlgorithmException {
        String oldPassword = Encryption.encryptPassword("password");
        user.setConfirmedPassword("NewPassword123");
        user.setReconfirmedPassword("NewPassword123");
        when(userPersistenceMock.getPassword("test@gmail.com")).thenReturn(oldPassword);
        assertFalse("Password format is not correct", updateUserInformation.updateUserPassword
                (userPersistenceMock, user.getPassword(),user.getConfirmedPassword(), user.getReconfirmedPassword()));
    }

    @Test
    @WithMockUser(username = "test@gmail.com")
    public void validatingUpdatedPasswordHasAtleastOneCapitalCharacter() throws SQLException, NoSuchAlgorithmException {
        String oldPassword = Encryption.encryptPassword("password");
        user.setConfirmedPassword("newpass@123");
        user.setReconfirmedPassword("newpass@123");
        when(userPersistenceMock.getPassword("test@gmail.com")).thenReturn(oldPassword);
        assertFalse("Password format is not correct", updateUserInformation.updateUserPassword
                (userPersistenceMock, user.getPassword(),user.getConfirmedPassword(), user.getReconfirmedPassword()));
    }


    @Test
    @WithMockUser(username = "test@gmail.com")
    public void validatingUpdatedPasswordHasAtleastOneSmallCharacter() throws SQLException, NoSuchAlgorithmException {
        String oldPassword = Encryption.encryptPassword("password");
        user.setConfirmedPassword("NEWPASSWORD@123");
        user.setReconfirmedPassword("NEWPASSWORD@123");
        when(userPersistenceMock.getPassword("test@gmail.com")).thenReturn(oldPassword);
        assertFalse("Password format is not correct", updateUserInformation.updateUserPassword
                (userPersistenceMock, user.getPassword(),user.getConfirmedPassword(), user.getReconfirmedPassword()));
    }


}