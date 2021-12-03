package com.team5.HAPark.user;

import com.team5.HAPark.user.DAO.IUserPersistence;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;

import java.security.NoSuchAlgorithmException;
import org.junit.jupiter.api.Test;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@SpringBootTest
public class UpdateUserInformationTest {

    private static UpdateUserInformation updateUserInformation;
    private static User user;
    private static IUserPersistence userPersistenceMock;

    @BeforeEach
    void init(){
        user = new User();
        user.setConfirmedPassword("NewPassword@123");
        user.setReconfirmedPassword("NewPassword@123");
        updateUserInformation = new UpdateUserInformation(user);
        userPersistenceMock = Mockito.mock(IUserPersistence.class);
    }

    @Test
    @WithMockUser(username = "user123")
    public void validUpdatedUserPassword() throws SQLException, NoSuchAlgorithmException {

        assertFalse(updateUserInformation.updateUserPassword
                (userPersistenceMock, user.getConfirmedPassword(), user.getReconfirmedPassword()));
    }
}