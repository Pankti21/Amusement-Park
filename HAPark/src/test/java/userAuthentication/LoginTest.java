package userAuthentication;

import database.IUserPersistence;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class LoginTest {
    private static Login loginRegister;
    private static User user;
    private static IUserPersistence userPersistenceMock;

    @BeforeAll
    static void init(){
        loginRegister = new Login(new User());
        user = new User();
        user.setEmail("test@gmail.com");
        user.setPassword("password");
        userPersistenceMock = Mockito.mock(IUserPersistence.class);
    }

    @Nested
    @DisplayName("Login method tests")
    class LoginTests {

        @BeforeEach
        void resetUser(){
            CurrentUser.getInstance().setUser(null);
        }

        @Test
        void loginUserDoesNotExist () {
            when(userPersistenceMock.doesUserExist(user.getEmail())).thenReturn(false);
            Login loginRegister = new Login(user);
            assertFalse(loginRegister.login(userPersistenceMock));
        }

        @Test
        void loginUserWrongPassword () {
            when(userPersistenceMock.doesUserExist(user.getEmail())).thenReturn(true);
            when(userPersistenceMock.getPassword(user.getEmail())).thenReturn("diffPassword");
            Login loginRegister = new Login(user);
            assertFalse(loginRegister.login(userPersistenceMock));
        }

        @Test
        void loginUserCorrectPassword () {
            when(userPersistenceMock.doesUserExist(user.getEmail())).thenReturn(true);
            when(userPersistenceMock.getPassword(user.getEmail())).thenReturn(user.getPassword());
            Login loginRegister = new Login(user);
            assertTrue(loginRegister.login(userPersistenceMock));
        }

        @Test
        void loginUserSuccessUpdatesCurrentUser() {
            CurrentUser.getInstance().setUser(null);
            when(userPersistenceMock.doesUserExist(user.getEmail())).thenReturn(true);
            when(userPersistenceMock.getPassword(user.getEmail())).thenReturn(user.getPassword());
            Login loginRegister = new Login(user);
            loginRegister.login(userPersistenceMock);
            assertEquals(CurrentUser.getInstance().getUser(),user);
        }

        @Test
        void loginUserFailsDueToEmail() {
            when(userPersistenceMock.doesUserExist(user.getEmail())).thenReturn(false);
            Login loginRegister = new Login(user);
            loginRegister.login(userPersistenceMock);
            assertNotEquals(CurrentUser.getInstance().getUser(),user);
        }

        @Test
        void loginUserFailsDueToPassword() {
            when(userPersistenceMock.doesUserExist(user.getEmail())).thenReturn(true);
            when(userPersistenceMock.getPassword(user.getEmail())).thenReturn("wrongPassword");
            Login loginRegister = new Login(user);
            loginRegister.login(userPersistenceMock);
            assertNotEquals(CurrentUser.getInstance().getUser(),user);
        }
    }

    @Test
    void logout() {
        CurrentUser.getInstance().setUser(user);
        loginRegister.logout();
        assertNull(CurrentUser.getInstance().getUser());
    }


}