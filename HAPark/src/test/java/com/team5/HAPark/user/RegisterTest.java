package com.team5.HAPark.user;

import com.team5.HAPark.user.model.Register;
import com.team5.HAPark.user.persistence.IUserPersistence;
import com.team5.HAPark.user.model.User;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RegisterTest {

    private static Register register;
    private static User user;
    private static IUserPersistence userPersistenceMock;

    @BeforeEach
    void init(){
        user = new User("email@testmail.com","Password@123","fname","lname");
        register = new Register(user);
        userPersistenceMock = Mockito.mock(IUserPersistence.class);
    }

    @Nested
    @DisplayName("registration tests")
    class RegistrationTests {

        @Test
        void registerFailsEmailEmpty() throws SQLException {
            user.setEmail("");
            assertFalse(register.register(userPersistenceMock,"Password@123"));
        }

        @Test
        void registerFailsFnameEmpty() throws SQLException {
            user.setFirstName("");
            assertFalse(register.register( userPersistenceMock,"Password@123"));
        }

        @Test
        void registerFailsLnameEmpty() throws SQLException {
            user.setLastName("");
            assertFalse(register.register(userPersistenceMock,"Password@123"));
        }

        @Test
        void registerFailsPasswordEmpty() throws SQLException {
            user.setPassword("");
            assertFalse(register.register( userPersistenceMock,"Password@123"));
        }

        @Test
        void registerFailsConfirmPasswordEmpty() throws SQLException {
            assertFalse(register.register( userPersistenceMock,""));
        }

        @Test
        void registerFailsEmailNull() throws SQLException {
            user.setEmail(null);
            assertFalse(register.register(userPersistenceMock,"Password@123"));
        }

        @Test
        void registerFailsFnameNull() throws SQLException {
            user.setFirstName(null);
            assertFalse(register.register(userPersistenceMock,"Password@123"));
        }

        @Test
        void registerFailsLnameNull() throws SQLException {
            user.setLastName(null);
            assertFalse(register.register(userPersistenceMock,"Password@123"));
        }

        @Test
        void registerFailsPasswordNull() throws SQLException {
            user.setPassword(null);
            assertFalse(register.register(userPersistenceMock,"Password@123"));
        }

        @Test
        void registerFailsConfirmPasswordNull() throws SQLException {
            assertFalse(register.register(userPersistenceMock,null));
        }

        @Test
        void registerFailsPasswordsDontMatch() throws SQLException {
            assertFalse(register.register(userPersistenceMock,"diffPassword"));
        }

        @Test
        void registerFailsUserAlreadyExists() throws SQLException {
            when(userPersistenceMock.doesUserExist(user.getEmail())).thenReturn(true);
            assertFalse(register.register(userPersistenceMock,"Password@123"));
            verify(userPersistenceMock,times(1)).doesUserExist(user.getEmail());
        }

        @Test
        void registerFailsBadEmailFormat() throws SQLException {
            user.setEmail("notAnEmail");
            assertFalse(register.register(userPersistenceMock,"Password@123"));
        }

        @Test
        void registerFailsBadPasswordFormat() throws SQLException {
            user.setPassword("badPassword");
            assertFalse(register.register(userPersistenceMock,"badPassword"));
        }

        @Test
        void registerSuccessful() throws SQLException {
            when(userPersistenceMock.doesUserExist(user.getEmail())).thenReturn(false);
            assertTrue(register.register(userPersistenceMock,"Password@123"));
            verify(userPersistenceMock,times(1)).doesUserExist(user.getEmail());
            Mockito.verify(userPersistenceMock, times(1))
                    .saveUser(user.getEmail(),user.getFirstName(),user.getLastName(),"ff7bd97b1a7789ddd2775122fd6817f3173672da9f802ceec57f284325bf589f");
        }
    }
}

