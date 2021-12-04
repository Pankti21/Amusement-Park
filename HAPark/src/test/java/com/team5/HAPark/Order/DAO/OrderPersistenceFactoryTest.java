package com.team5.HAPark.Order.DAO;

import com.team5.HAPark.Food.IFoodService;
import com.team5.HAPark.Ticket.ITicketService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class OrderPersistenceFactoryTest {

    private IOrderPersistence foodOrderPersistence1;
    private IOrderPersistence foodOrderPersistence2;

    private IOrderPersistence ticketOrderPersistence1;
    private IOrderPersistence ticketOrderPersistence2;

    @BeforeEach
    void setUp() {

        IFoodService foodService = Mockito.mock(IFoodService.class);
        ITicketService ticketService = Mockito.mock(ITicketService.class);

        OrderPersistenceFactory orderPersistenceFactory1 = new OrderPersistenceFactory();
        OrderPersistenceFactory orderPersistenceFactory2 = new OrderPersistenceFactory();

        foodOrderPersistence1 = orderPersistenceFactory1.createFoodOrderPersistence(foodService);
        foodOrderPersistence2 = orderPersistenceFactory2.createFoodOrderPersistence(foodService);

        ticketOrderPersistence1 = orderPersistenceFactory1.createTicketOrderPersistence(ticketService);
        ticketOrderPersistence2 = orderPersistenceFactory2.createTicketOrderPersistence(ticketService);
    }

    @Test
    void createFoodOrderPersistenceSameInstance() {
        assertEquals(foodOrderPersistence1,foodOrderPersistence2);
    }

    @Test
    void createFoodOrderPersistenceIsFoodOrder() {
        assertTrue(foodOrderPersistence1 instanceof MySQLFoodOrderPersistence);
    }

    @Test
    void createFoodOrderPersistenceNotNull() {
        assertNotNull(foodOrderPersistence1);
    }

    @Test
    void createTicketOrderPersistenceSameInstance() {
        assertEquals(ticketOrderPersistence1,ticketOrderPersistence2);
    }

    @Test
    void createTicketOrderPersistenceIsTicketOrder() {
        assertTrue(ticketOrderPersistence1 instanceof MySQLTicketOrderPersistence);
    }

    @Test
    void createTicketOrderPersistenceNotNull() {
        assertNotNull(ticketOrderPersistence1);
    }

}