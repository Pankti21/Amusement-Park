package com.team5.HAPark.user;

import com.team5.HAPark.user.model.Encryption;
import com.team5.HAPark.user.model.Login;
import com.team5.HAPark.user.persistence.IUserPersistence;
import com.team5.HAPark.user.model.UserCredentials;
import com.team5.HAPark.user.persistence.mocks.UserPersistenceMockFactory;
import org.junit.jupiter.api.*;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class LoginTest {

    private static Login login;
    private static UserCredentials user;
    private static IUserPersistence userPersistenceMock;

    @BeforeEach
    void init() throws SQLException, NoSuchAlgorithmException {
        user = new UserCredentials();
        user.setEmail("test@gmail.com");
        user.setPassword("password");
        login = new Login(user);
        UserPersistenceMockFactory factory = new UserPersistenceMockFactory();
        userPersistenceMock = factory.createUserPersistenceMock();
        String encryptedPassword = Encryption.encryptPassword("password");
        userPersistenceMock.saveUser("test@gmail.com","fname", "lname", encryptedPassword);
    }

    @Test
    void loginUserDoesNotExist () throws SQLException {
        user.setEmail("emailNotPresent");
        Login login = new Login(user);
        assertFalse(login.login(userPersistenceMock));
    }

    @Test
    void loginUserSuccessful() throws SQLException {
        Login login = new Login(user);
        assertTrue(login.login(userPersistenceMock));
    }

    @Test
    void loginUserWrongPassword() throws NoSuchAlgorithmException {
        user.setPassword("wrongPassword");
        Login login = new Login(user);
        assertFalse(login.login(userPersistenceMock));
    }
}
