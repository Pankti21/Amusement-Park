package com.team5.HAPark.Order;

import com.team5.HAPark.Order.DAO.ITicketOrderPersistence;
import com.team5.HAPark.Ticket.Ticket;
import com.team5.HAPark.Ticket.TicketOrderItem;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;

class TicketOrderServiceTest {

    private static TicketOrderService ticketOrderService;
    private static ITicketOrderPersistence orderPersistenceMock;
    private static List<TicketOrderItem> orderItems;
    private static Ticket child;
    private static Ticket adult;
    private static TicketOrderItem childTicketOrder;
    private static TicketOrderItem adultTicketOrder;
    private static TicketOrder order;

    @BeforeAll
    public static void init(){

        orderPersistenceMock = Mockito.mock(ITicketOrderPersistence.class);
        ticketOrderService = new TicketOrderService(orderPersistenceMock);
        orderItems = new ArrayList<>();

        child = new Ticket("child",15);
        adult = new Ticket("adult",20);

        childTicketOrder = new TicketOrderItem(child,3);
        adultTicketOrder = new TicketOrderItem(adult,2);

        orderItems.add(childTicketOrder);
        orderItems.add(adultTicketOrder);

        LocalTime time = LocalTime.now();
        LocalDate date = LocalDate.now();

        order = new TicketOrder();
        order.setOrderDate(date);
        order.setOrderTime(time);
        order.setMailId("email");
    }

    @AfterEach
    public void reset(){
        Mockito.clearInvocations(orderPersistenceMock);
    }

    @Test
    public void createOrderFromItemQuantitiesHasItems() {

        TicketOrder newOrder = ticketOrderService.createOrderFromItemQuantities("email", orderItems);
        List<TicketOrderItem> orderItems = newOrder.getOrderItems();

        assertTrue(orderItems.contains(childTicketOrder));
        assertTrue(orderItems.contains(adultTicketOrder));
    }

    @Test
    public void createOrderFromItemQuantitiesHasCorrectEmail() {

        TicketOrder newOrder = ticketOrderService.createOrderFromItemQuantities("email", orderItems);
        List<TicketOrderItem> orderItems = newOrder.getOrderItems();

        assertEquals("email",newOrder.getMailId());
    }

    @Test
    public void createOrderFromItemQuantitiesHasDateTime() {

        TicketOrder newOrder = ticketOrderService.createOrderFromItemQuantities("email", orderItems);
        List<TicketOrderItem> orderItems = newOrder.getOrderItems();

        assertNotNull(newOrder.getOrderDate());
        assertNotNull(newOrder.getOrderDate());
    }

    @Test
    public void createOrderFromItemQuantitiesDefaultHasNoOrderId() {

        TicketOrder newOrder = ticketOrderService.createOrderFromItemQuantities("email", orderItems);
        List<TicketOrderItem> orderItems = newOrder.getOrderItems();

        assertNull(newOrder.getOrderId());
    }

    @Test
    public void createOrderFromItemQuantitiesEmptyItems() {

        TicketOrder newOrder = ticketOrderService.createOrderFromItemQuantities("email", new ArrayList<>());

        assertNull(newOrder);
    }

    @Test
    public void createOrderFromItemQuantitiesNullItems() {

        TicketOrder newOrder = ticketOrderService.createOrderFromItemQuantities("email",null);

        assertNull(newOrder);
    }

    @Test
    public void saveOrder() throws SQLException {

        ticketOrderService.saveOrder(order);
        Mockito.verify(orderPersistenceMock,times(1)).saveOrder(order);

    }

    @Test
    public void getOrder() throws SQLException {

        Mockito.when(orderPersistenceMock.loadOrder(10)).thenReturn(new TicketOrder());
        assertNotNull(ticketOrderService.getOrder(10));
        Mockito.verify(orderPersistenceMock,times(1)).loadOrder(10);

    }

    @Test
    public void getOrderDoesntExist() throws SQLException {

        Mockito.when(orderPersistenceMock.loadOrder(10)).thenReturn(null);
        assertNull(ticketOrderService.getOrder(10));
        Mockito.verify(orderPersistenceMock,times(1)).loadOrder(10);

    }
    @Test
    public void getAllOrdersForUser() throws SQLException {

        ArrayList<TicketOrder> orders = new ArrayList<>();
        orders.add(order);

        Mockito.when(orderPersistenceMock.loadAllOrders("email")).thenReturn(orders);

        assertEquals(orders, ticketOrderService.getAllOrdersForUser("email"));
        Mockito.verify(orderPersistenceMock,times(1)).loadAllOrders("email");

    }

    @Test
    public void getAllOrdersForUserButUserHasNoOrders() throws SQLException {

        Mockito.when(orderPersistenceMock.loadAllOrders("email")).thenReturn(null);

        assertTrue(ticketOrderService.getAllOrdersForUser("email").isEmpty());
        Mockito.verify(orderPersistenceMock,times(1)).loadAllOrders("email");

    }
}