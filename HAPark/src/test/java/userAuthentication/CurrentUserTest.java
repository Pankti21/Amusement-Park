package userAuthentication;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CurrentUserTest {

    @Test
    void getInstanceNotNull() {
        CurrentUser currentUser = CurrentUser.getInstance();
        assertNotNull(currentUser);
    }

    @Test
    void getInstanceSameInstance() {
        CurrentUser currentUser = CurrentUser.getInstance();
        assertEquals(currentUser, CurrentUser.getInstance());
    }
}