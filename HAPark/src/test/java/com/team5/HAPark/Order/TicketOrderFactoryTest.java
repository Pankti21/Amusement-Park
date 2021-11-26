package com.team5.HAPark.Order;

import com.team5.HAPark.Order.DAO.IOrderPersistence;
import com.team5.HAPark.database.mysql.MySQLDatabase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class TicketOrderFactoryTest {

    private TicketOrderFactory ticketOrderFactory;
    private MySQLDatabase database;

    @BeforeEach
    void setUp() {
        database = Mockito.mock(MySQLDatabase.class);
        ticketOrderFactory = new TicketOrderFactory();
    }

    @Test
    void createOrderPersistence() throws SQLException {
        IOrderPersistence orderPersistence = ticketOrderFactory.createOrderPersistence(database);
        assertNotNull(orderPersistence);
    }
}