package com.team5.HAPark.userAuthentication;

import database.IUserPersistence;

import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;


import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
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
        void registerFailsEmailEmpty() throws SQLException {
            user.setEmail("");
            assertFalse(register.register(userPersistenceMock,"Password@123"));
        }

        @Test
        void registerFailsFnameEmpty() throws SQLException {
            user.setFirstName("");
            assertFalse(register.register(userPersistenceMock,"Password@123"));
        }

        @Test
        void registerFailsLnameEmpty() throws SQLException {
            user.setLastName("");
            assertFalse(register.register(userPersistenceMock,"Password@123"));
        }

        @Test
        void registerFailsPasswordEmpty() throws SQLException {
            user.setPassword("");
            assertFalse(register.register(userPersistenceMock,"Password@123"));
        }

        @Test
        void registerFailsConfirmPasswordEmpty() throws SQLException {
            assertFalse(register.register(userPersistenceMock,""));
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
                    .saveUser(user.getFirstName(),user.getLastName(),user.getEmail(),user.getPassword());
        }
    }

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

