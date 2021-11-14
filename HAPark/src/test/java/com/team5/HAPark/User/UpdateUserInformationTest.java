/*
package com.team5.HAPark.User;

import com.team5.HAPark.User.DAO.IUserPersistence;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import org.springframework.security.test.context.support.WithMockUser;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
*/
/*
public class UpdateUserInformationTest {
    private static UpdateUserInformation updateUserInformation;
    private static User user;
    private static IUserPersistence userPersistenceMock;

    @BeforeEach
    void init(){
        user = new User("Password@123","Password@123");
        updateUserInformation = new UpdateUserInformation(user);
        userPersistenceMock = Mockito.mock(IUserPersistence.class);
    }
    @Test@WithMockUser(username = "email@testmail.com", password = "Password@123")
    public void validUpdatedUserPassword() throws SQLException, NoSuchAlgorithmException {
        assertEquals(true, updateUserInformation.updateUserPassword(userPersistenceMock,"NewPassword@123","NewPassword@123"));
    }


}
*/