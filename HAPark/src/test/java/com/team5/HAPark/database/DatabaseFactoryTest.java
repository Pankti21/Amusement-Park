package com.team5.HAPark.database;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseFactoryTest {

    private IDataBase dataBase1;
    private IDataBase dataBase2;

    @BeforeEach
    void setUp() {
        DatabaseFactory databaseFactory1 = new DatabaseFactory();
        dataBase1 = databaseFactory1.createDatabase();

        DatabaseFactory databaseFactory2 = new DatabaseFactory();
        dataBase2 = databaseFactory2.createDatabase();
    }

    @Test
    void createDatabaseGivesSameDatabase() {
        assertEquals(dataBase1,dataBase2);
    }

    @Test
    void createDatabaseGivesNotNull() {
        assertNotNull(dataBase1);
    }
}