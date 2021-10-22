package userAuthentication;

import database.IUserPersistence;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import userAuthentication.Login;
import userAuthentication.Register;
import userAuthentication.User;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class RegisterTest {
    private static Register register;
    private static User user;
    private static IUserPersistence userPersistenceMock;
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
            User user = new User();
            user.setPassword("Dal@123");
            Register register = new Register(user);
            assertFalse(register.validatePasswordFormat());
        }

        @Test
        void validatePasswordFormatHasMaximum12Chars() {
            User user = new User();
            user.setPassword("Dalhousie@123");
            Register register = new Register(user);
            assertFalse(register.validatePasswordFormat());
        }

        @Test
        void validatePasswordFormatHasAtleastOneUpperCaseChar() {
            User user = new User();
            user.setPassword("dal@1234");
            Register register = new Register(user);
            assertFalse(register.validatePasswordFormat());
        }

        @Test
        void validatePasswordFormatHasAtleastOneLowerCaseChar() {
            User user = new User();
            user.setPassword("DAL@1234");
            Register register = new Register(user);
            assertFalse(register.validatePasswordFormat());
        }

        @Test
        void validatePasswordFormatHasAtleastOneNumber() {
            User user = new User();
            user.setPassword("Dal@housie");
            Register register = new Register(user);
            assertFalse(register.validatePasswordFormat());
        }

        @Test
        void validatePasswordFormatHasAtleastOneSpecialChar() {
            User user = new User();
            user.setPassword("Dalhousie123");
            Register register = new Register(user);
            assertFalse(register.validatePasswordFormat());
        }
    }

    @Nested
    @DisplayName("validate email method tests")
    class ValidateEmailTests {
        @Test
        void validateEmailFormatNoAtSymbol() {
            User user = new User();
            user.setEmail("sample.gmail.com");
            Register register = new Register(user);
            assertFalse(register.validateEmailFormat());
        }

        @Test
        void validateEmailFormatMultipleAtSymbol() {
            User user = new User();
            user.setEmail("sample@email@gmail.com");
            Register register = new Register(user);
            assertFalse(register.validateEmailFormat());
        }

        @Test
        void validateEmailFormatNoDomain() {
            User user = new User();
            user.setEmail("sample@com");
            Register register = new Register(user);
            assertFalse(register.validateEmailFormat());
        }

        @Test
        void validateEmailFormatLocalLongerThan64Char() {
            User user = new User();
            user.setEmail("samplesamplesamplesamplesamplesamplesamplesamplesamplesample12345@email.com");
            Register register = new Register(user);
            assertFalse(register.validateEmailFormat());
        }

        @Test
        void validateEmailFormatStartsWithPeriod() {
            User user = new User();
            user.setEmail(".sample@email.com");
            Register register = new Register(user);
            assertFalse(register.validateEmailFormat());
        }

        @Test
        void validateEmailFormatEndsWithPeriod() {
            User user = new User();
            user.setEmail("sample@email.com.");
            Register register = new Register(user);
            assertFalse(register.validateEmailFormat());
        }

        @Test
        void validateEmailFormatValidEmail() {
            User user = new User();
            user.setEmail("emailsample@email.com");
            Register register = new Register(user);
            assertTrue(register.validateEmailFormat());
        }
    }
}

