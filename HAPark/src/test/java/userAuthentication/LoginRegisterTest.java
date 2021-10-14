package userAuthentication;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoginRegisterTest {
    LoginRegister loginRegister = new LoginRegister();

  /*  @Test
    void login() {
        //need to mock db
        //create class to hold loggedin user
        //TODO
    }

    @Test
    void register() {
    }
*/

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
    void validatePasswordFormatHasAtleastOneLowerCaseChar(){
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