package com.team5.HAPark.User;

import com.team5.HAPark.User.DAO.IUserPersistence;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;


@SpringBootTest
class RegisterTest {
    private static Register register;
    private static User user;
    private static IUserPersistence userPersistenceMock;

    @BeforeEach
    void init(){
        user = new User("fname","lname","email@testmail.com","Password@123");
        register = new Register(user);
        userPersistenceMock = Mockito.mock(IUserPersistence.class);

    }

    @Nested
    @DisplayName("registration tests")
    class RegistrationTests {

        @Test
        @WithMockUser(username = "user123")
        void registerFailsEmailEmpty() throws SQLException {
            user.setEmail("");
            assertFalse(register.register(userPersistenceMock,"Password@123"));
        }

        @Test
        @WithMockUser(username = "user123")
        void registerFailsFnameEmpty() throws SQLException {
            user.setFirstName("");
            assertFalse(register.register(userPersistenceMock,"Password@123"));
        }

        @Test
        @WithMockUser(username = "user123")
        void registerFailsLnameEmpty() throws SQLException {
            user.setLastName("");
            assertFalse(register.register(userPersistenceMock,"Password@123"));
        }

        @Test
        @WithMockUser(username = "user123")
        void registerFailsPasswordEmpty() throws SQLException {
            user.setPassword("");
            assertFalse(register.register(userPersistenceMock,"Password@123"));
        }

        @Test
        @WithMockUser(username = "user123")
        void registerFailsConfirmPasswordEmpty() throws SQLException {
            assertFalse(register.register(userPersistenceMock,""));
        }

        @Test
        @WithMockUser(username = "user123")
        void registerFailsEmailNull() throws SQLException {
            user.setEmail(null);
            assertFalse(register.register(userPersistenceMock,"Password@123"));
        }

        @Test
        @WithMockUser(username = "user123")
        void registerFailsFnameNull() throws SQLException {
            user.setFirstName(null);
            assertFalse(register.register(userPersistenceMock,"Password@123"));
        }

        @Test
        @WithMockUser(username = "user123")
        void registerFailsLnameNull() throws SQLException {
            user.setLastName(null);
            assertFalse(register.register(userPersistenceMock,"Password@123"));
        }

        @Test
        @WithMockUser(username = "user123")
        void registerFailsPasswordNull() throws SQLException {
            user.setPassword(null);
            assertFalse(register.register(userPersistenceMock,"Password@123"));
        }

        @Test
        @WithMockUser(username = "user123")
        void registerFailsConfirmPasswordNull() throws SQLException {
            assertFalse(register.register(userPersistenceMock,null));
        }

        @Test
        @WithMockUser(username = "user123")
        void registerFailsPasswordsDontMatch() throws SQLException {
            assertFalse(register.register(userPersistenceMock,"diffPassword"));
        }

        @Test
        @WithMockUser(username = "user123")
        void registerFailsUserAlreadyExists() throws SQLException {
            when(userPersistenceMock.doesUserExist(user.getEmail())).thenReturn(true);
            assertFalse(register.register(userPersistenceMock,"Password@123"));
            verify(userPersistenceMock,times(1)).doesUserExist(user.getEmail());
        }

        @Test
        @WithMockUser(username = "user123")
        void registerFailsBadEmailFormat() throws SQLException {
            user.setEmail("notAnEmail");
            assertFalse(register.register(userPersistenceMock,"Password@123"));
        }

        @Test
        @WithMockUser(username = "user123")
        void registerFailsBadPasswordFormat() throws SQLException {
            user.setPassword("badPassword");
            assertFalse(register.register(userPersistenceMock,"badPassword"));
        }

        @Test
        @WithMockUser(username = "user123")
        void registerSuccessful() throws SQLException {
            when(userPersistenceMock.doesUserExist(user.getEmail())).thenReturn(false);
            assertTrue(register.register(userPersistenceMock,"Password@123"));
            verify(userPersistenceMock,times(1)).doesUserExist(user.getEmail());
            Mockito.verify(userPersistenceMock, times(1))
                    .saveUser(user.getEmail(),user.getFirstName(),user.getLastName(),"ff7bd97b1a7789ddd2775122fd6817f3173672da9f802ceec57f284325bf589f");
        }
    }


}

