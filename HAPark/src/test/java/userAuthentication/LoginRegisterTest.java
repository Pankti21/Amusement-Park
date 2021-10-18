package userAuthentication;

import database.IUserPersistence;
import org.junit.Before;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class LoginRegisterTest {
    private static LoginRegister loginRegister;
    private static User user;
    private static IUserPersistence userPersistenceMock;

    @BeforeAll
    static void init(){
        loginRegister = new LoginRegister(new User());
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
            System.out.println("HELLO");
            CurrentUser.getInstance().setUser(null);
        }

        @Test
        void loginUserDoesNotExist () {
            when(userPersistenceMock.doesUserExist(user.getEmail())).thenReturn(false);
            LoginRegister loginRegister = new LoginRegister(user);
            assertFalse(loginRegister.login(userPersistenceMock));
        }

        @Test
        void loginUserWrongPassword () {
            when(userPersistenceMock.doesUserExist(user.getEmail())).thenReturn(true);
            when(userPersistenceMock.getPassword(user.getEmail())).thenReturn("diffPassword");
            LoginRegister loginRegister = new LoginRegister(user);
            assertFalse(loginRegister.login(userPersistenceMock));
        }

        @Test
        void loginUserCorrectPassword () {
            when(userPersistenceMock.doesUserExist(user.getEmail())).thenReturn(true);
            when(userPersistenceMock.getPassword(user.getEmail())).thenReturn(user.getPassword());
            LoginRegister loginRegister = new LoginRegister(user);
            assertTrue(loginRegister.login(userPersistenceMock));
        }

        @Test
        void loginUserSuccessUpdatesCurrentUser() {
            CurrentUser.getInstance().setUser(null);
            when(userPersistenceMock.doesUserExist(user.getEmail())).thenReturn(true);
            when(userPersistenceMock.getPassword(user.getEmail())).thenReturn(user.getPassword());
            LoginRegister loginRegister = new LoginRegister(user);
            loginRegister.login(userPersistenceMock);
            assertEquals(CurrentUser.getInstance().getUser(),user);
        }

        @Test
        void loginUserFailsDueToEmail() {
            when(userPersistenceMock.doesUserExist(user.getEmail())).thenReturn(false);
            LoginRegister loginRegister = new LoginRegister(user);
            loginRegister.login(userPersistenceMock);
            assertNotEquals(CurrentUser.getInstance().getUser(),user);
        }

        @Test
        void loginUserFailsDueToPassword() {
            when(userPersistenceMock.doesUserExist(user.getEmail())).thenReturn(true);
            when(userPersistenceMock.getPassword(user.getEmail())).thenReturn("wrongPassword");
            LoginRegister loginRegister = new LoginRegister(user);
            loginRegister.login(userPersistenceMock);
            assertNotEquals(CurrentUser.getInstance().getUser(),user);
        }
    }

    /*
    @Test
    void register() {
    }
*/

    @Nested
    @DisplayName("validate password method tests")
    class ValidatePasswordTests {
        /* Test cases for Password Validation */
        @Test
        void validatePasswordFormatHasMinimum8Chars() {
            assertFalse(loginRegister.validatePasswordFormat("Dal@123"));
        }

        @Test
        void validatePasswordFormatHasMaximum12Chars() {
            assertFalse(loginRegister.validatePasswordFormat("Dalhousie@123"));
        }

        @Test
        void validatePasswordFormatHasAtleastOneUpperCaseChar() {
            assertFalse(loginRegister.validatePasswordFormat("dal@1234"));
        }

        @Test
        void validatePasswordFormatHasAtleastOneLowerCaseChar() {
            assertFalse(loginRegister.validatePasswordFormat("DAL@1234"));
        }

        @Test
        void validatePasswordFormatHasAtleastOneNumber() {
            assertFalse(loginRegister.validatePasswordFormat("Dal@housie"));
        }

        @Test
        void validatePasswordFormatHasAtleastOneSpecialChar() {
            assertFalse(loginRegister.validatePasswordFormat("Dalhousie123"));
        }
    }

    @Nested
    @DisplayName("validate email method tests")
    class ValidateEmailTests {
        @Test
        void validateEmailFormatNoAtSymbol() {
            User user = new User();
            user.setEmail("sample.gmail.com");
            LoginRegister loginRegister = new LoginRegister(user);
            assertFalse(loginRegister.validateEmailFormat());
        }

        @Test
        void validateEmailFormatMultipleAtSymbol() {
            User user = new User();
            user.setEmail("sample@email@gmail.com");
            LoginRegister loginRegister = new LoginRegister(user);
            assertFalse(loginRegister.validateEmailFormat());
        }

        @Test
        void validateEmailFormatNoDomain() {
            User user = new User();
            user.setEmail("sample@com");
            LoginRegister loginRegister = new LoginRegister(user);
            assertFalse(loginRegister.validateEmailFormat());
        }

        @Test
        void validateEmailFormatLocalLongerThan64Char() {
            User user = new User();
            user.setEmail("samplesamplesamplesamplesamplesamplesamplesamplesamplesample12345@email.com");
            LoginRegister loginRegister = new LoginRegister(user);
            assertFalse(loginRegister.validateEmailFormat());
        }

        @Test
        void validateEmailFormatStartsWithPeriod() {
            User user = new User();
            user.setEmail(".sample@email.com");
            LoginRegister loginRegister = new LoginRegister(user);
            assertFalse(loginRegister.validateEmailFormat());
        }

        @Test
        void validateEmailFormatEndsWithPeriod() {
            User user = new User();
            user.setEmail("sample@email.com.");
            LoginRegister loginRegister = new LoginRegister(user);
            assertFalse(loginRegister.validateEmailFormat());
        }

        @Test
        void validateEmailFormatValidEmail() {
            User user = new User();
            user.setEmail("emailsample@email.com");
            LoginRegister loginRegister = new LoginRegister(user);
            assertTrue(loginRegister.validateEmailFormat());
        }
    }
}