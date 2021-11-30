 //package com.team5.HAPark.User;
/*
import com.team5.HAPark.User.DAO.IUserPersistence;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;

import java.security.NoSuchAlgorithmException;
import org.junit.jupiter.api.Test;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

@SpringBootTest
public class UpdateUserInformationTest {

    private static UpdateUserInformation updateUserInformation;
    private static User user;
    private static User user1;
    private static IUserPersistence userPersistenceMock;

    @BeforeEach
    void init(){
        user1 = new User();
        user1.setPassword("OldPassword2@123");
        user1.setConfirmedPassword("NewPassword2@123");
        user1.setReconfirmedPassword("NewPassword2@123");
        updateUserInformation = new UpdateUserInformation(user1);
        user = new User();
        user.setPassword("OldPassword@123");
        user.setConfirmedPassword("NewPassword@123");
        user.setReconfirmedPassword("NewPassword@123");
        user.setEmail("test@gmail.com");
        updateUserInformation = new UpdateUserInformation(user);
        userPersistenceMock = Mockito.mock(IUserPersistence.class);
    }

    @Test
    @WithMockUser(username = "user123",password = "password")
    public void validateUpdatedUserPassword() throws SQLException, NoSuchAlgorithmException {
        assertEquals(false,
                updateUserInformation.updateUserPassword
                        (userPersistenceMock, user.getPassword(),user.getConfirmedPassword(), user.getReconfirmedPassword()));
    }

    @Test
    @WithMockUser(username = "user123",password = "password")
    public void validUpdatedUserPassword2() throws SQLException, NoSuchAlgorithmException {
        //System.out.println("password :" + user1.getPassword());
        assertEquals(false,
                updateUserInformation.updateUserPassword
                        (userPersistenceMock, user1.getPassword(),user1.getConfirmedPassword(), user1.getReconfirmedPassword()));
    }
}*/