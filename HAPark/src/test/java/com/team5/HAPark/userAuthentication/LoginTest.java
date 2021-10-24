package com.team5.HAPark.userAuthentication;

import database.IUserPersistence;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class LoginTest {

    private static Login login;
    private static User user;
    private static IUserPersistence userPersistenceMock;

    @BeforeEach
    void init(){
        user = new User();
        user.setEmail("test@gmail.com");
        user.setPassword("password");
        login = new Login(user);
        userPersistenceMock = Mockito.mock(IUserPersistence.class);
    }

    @Test
    void loginUserDoesNotExist () throws SQLException {
        when(userPersistenceMock.doesUserExist(user.getEmail())).thenReturn(false);
        Login loginRegister = new Login(user);
        assertFalse(loginRegister.login(userPersistenceMock));
    }

    @Test
    void loginUserWrongPassword () throws SQLException {
        when(userPersistenceMock.doesUserExist(user.getEmail())).thenReturn(true);
        when(userPersistenceMock.getPassword(user.getEmail())).thenReturn("diffPassword");
        Login loginRegister = new Login(user);
        assertFalse(loginRegister.login(userPersistenceMock));
    }

    @Test
    void loginUserCorrectPassword () throws SQLException {
        when(userPersistenceMock.doesUserExist(user.getEmail())).thenReturn(true);
        when(userPersistenceMock.getPassword(user.getEmail())).thenReturn(user.getPassword());
        Login Login = new Login(user);
        assertTrue(Login.login(userPersistenceMock));
    }

    @Test
    void loginUserSuccessful() throws SQLException {
        when(userPersistenceMock.doesUserExist(user.getEmail())).thenReturn(true);
        when(userPersistenceMock.getPassword(user.getEmail())).thenReturn(user.getPassword());
        when(userPersistenceMock.loadUser(user.getEmail())).thenReturn(new User("fname", "lname", user.getEmail(), user.getPassword()));
        Login Login = new Login(user);
        assertTrue(login.login(userPersistenceMock));
    }

    @Test
    void loginUserFailsDueToEmail() throws SQLException {
        when(userPersistenceMock.doesUserExist(user.getEmail())).thenReturn(false);
        Login login = new Login(user);
        login.login(userPersistenceMock);
        assertFalse(login.login(userPersistenceMock));
    }

    @Test
    void loginUserFailsDueToPassword() throws SQLException {
        when(userPersistenceMock.doesUserExist(user.getEmail())).thenReturn(true);
        when(userPersistenceMock.getPassword(user.getEmail())).thenReturn("wrongPassword");
        Login login = new Login(user);
        assertFalse(login.login(userPersistenceMock));
    }
}