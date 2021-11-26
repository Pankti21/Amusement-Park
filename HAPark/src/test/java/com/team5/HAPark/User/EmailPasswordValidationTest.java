package com.team5.HAPark.User;

import com.team5.HAPark.User.DAO.IUserPersistence;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.springframework.security.test.context.support.WithMockUser;

import java.sql.SQLException;

@SpringBootTest
public class EmailPasswordValidationTest {

    private static User user;
    private static IUserPersistence userPersistenceMock;
    EmailPasswordValidation emailPasswordValidation ;

    @BeforeEach
    void init(){
        user = new User();
        user.setEmail("emailsample@email.com@123");
        emailPasswordValidation = new EmailPasswordValidation(user);
        userPersistenceMock = Mockito.mock(IUserPersistence.class);
    }

    @Nested
    @DisplayName("validate password method tests")
    class ValidatePasswordTests {
        /* Test cases for Password Validation */
        @Test
        @WithMockUser(username = "user123")
        void validatePasswordFormatHasMinimum8Chars() throws SQLException {
            user.setPassword("Dal@123");
            assertEquals(false,emailPasswordValidation.validatePasswordFormat());
        }

        @Test
        void validatePasswordFormatHasMaximum12Chars(){
            user.setPassword("Dalhousie@123");
            assertFalse(emailPasswordValidation.validatePasswordFormat());
        }

        @Test
        void validatePasswordFormatHasAtleastOneUpperCaseChar()  {
            user.setPassword("dal@1234");
            assertFalse(emailPasswordValidation.validatePasswordFormat());
        }

        @Test
        void validatePasswordFormatHasAtleastOneLowerCaseChar()  {
            user.setPassword("DAL@1234");
            assertFalse(emailPasswordValidation.validatePasswordFormat());
        }

        @Test
        void validatePasswordFormatHasAtleastOneNumber()  {
            user.setPassword("Dal@housie");
            assertFalse(emailPasswordValidation.validatePasswordFormat());
        }

        @Test
        void validatePasswordFormatHasAtleastOneSpecialChar() {
            user.setPassword("Dalhousie123");
            assertFalse(emailPasswordValidation.validatePasswordFormat());
        }
    }

    @Nested
    @DisplayName("validate email method tests")
    class ValidateEmailTests {
        @Test
        void validateEmailFormatNoAtSymbol() {
            user.setEmail("sample.gmail.com");
            assertFalse(emailPasswordValidation.validateEmailFormat());
        }

        @Test
        void validateEmailFormatMultipleAtSymbol() {
            user.setEmail("sample@email@gmail.com");
            assertFalse(emailPasswordValidation.validateEmailFormat());
        }

        @Test
        void validateEmailFormatNoDomain() {
            user.setEmail("sample@com");
            assertFalse(emailPasswordValidation.validateEmailFormat());
        }

        @Test
        void validateEmailFormatLocalLongerThan64Char() {
            user.setEmail("samplesamplesamplesamplesamplesamplesamplesamplesamplesample12345@email.com");
            assertFalse(emailPasswordValidation.validateEmailFormat());
        }

        @Test
        void validateEmailFormatStartsWithPeriod() {
            user.setEmail(".sample@email.com");
            assertFalse(emailPasswordValidation.validateEmailFormat());
        }

        @Test
        void validateEmailFormatEndsWithPeriod() {
            user.setEmail("sample@email.com.");
            assertFalse(emailPasswordValidation.validateEmailFormat());
        }

        @Test
        void validateEmailFormatValidEmail() {
            user.setEmail("emailsample@email.com");
            assertTrue(emailPasswordValidation.validateEmailFormat());
        }
    }

}
