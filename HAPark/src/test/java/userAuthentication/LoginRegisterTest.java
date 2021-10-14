package userAuthentication;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoginRegisterTest {

    @Test
    void login() {
        //need to mock db
        //create class to hold loggedin user
        //TODO
    }

    @Test
    void register() {
    }

    @Test
    void validateEmailFormatValidEmail() {
        User user = new User();
        user.setEmail("emailsample@email.com");
        LoginRegister loginRegister = new LoginRegister(user);
        assertTrue(loginRegister.validateEmailFormat());
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
    void validatePasswordFormat() {
    }
}