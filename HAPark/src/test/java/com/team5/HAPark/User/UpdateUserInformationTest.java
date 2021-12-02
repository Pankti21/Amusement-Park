package com.team5.HAPark.User;

import com.team5.HAPark.User.DAO.IUserPersistence;
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
    public void validateUpdatedUserPassword() throws SQLException, NoSuchAlgorithmException {
        String oldPassword = Encryption.encryptPassword("password");
        when(userPersistenceMock.getPassword("test@gmail.com")).thenReturn(oldPassword);
        assertFalse( updateUserInformation.updateUserPassword
                        (userPersistenceMock, user.getPassword(),user.getConfirmedPassword(), user.getReconfirmedPassword()));
    }

    @Test
    @WithMockUser(username = "test@gmail.com")
    public void validateUpdatedOtherUserPassword() throws SQLException, NoSuchAlgorithmException {
        user.setPassword("otherPassword");
        String oldPassword = Encryption.encryptPassword("otherPassword");
        when(userPersistenceMock.getPassword("test@gmail.com")).thenReturn(oldPassword);
        assertFalse( updateUserInformation.updateUserPassword
                (userPersistenceMock, user.getPassword(),user.getConfirmedPassword(), user.getReconfirmedPassword()));
    }

}