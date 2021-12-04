package com.team5.HAPark.User.DAO;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserPersistenceFactoryTest {

    private IUserPersistence userPersistence1;
    private IUserPersistence userPersistence2;

    @BeforeEach
    void setUp() {
        UserPersistenceFactory userPersistenceFactory1 = new UserPersistenceFactory();
        UserPersistenceFactory userPersistenceFactory2 = new UserPersistenceFactory();

        userPersistence1 = userPersistenceFactory1.createUserPersistence();
        userPersistence2 = userPersistenceFactory2.createUserPersistence();
    }

    @Test
    void createUserPersistenceSameInstance() {
        assertEquals(userPersistence1,userPersistence2);
    }

    @Test
    void createUserPersistenceNotNull() {
        assertNotNull(userPersistence1);
    }
}