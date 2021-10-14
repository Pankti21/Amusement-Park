package userAuthentication;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoginRegisterTest {
    LoginRegister loginRegister = new LoginRegister();

  /*  @Test
    void login() {
    }

    @Test
    void register() {
    }

    @Test
    void validateEmailFormat() {
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
}