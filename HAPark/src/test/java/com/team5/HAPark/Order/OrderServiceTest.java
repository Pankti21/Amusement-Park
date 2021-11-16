package com.team5.HAPark.Order;

import com.team5.HAPark.Food.Food;
import com.team5.HAPark.Order.DAO.IOrderPersistence;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class OrderServiceTest {

    private static OrderService orderService;
    private static IOrderPersistence orderPersistenceMock;
    private static Map<IItem,Integer> itemQuantities;
    private static IItem pizza;
    private static IItem burger;
    private static IItem fries;
    private static Order order;

    @BeforeAll
    public static void init(){

        orderPersistenceMock = Mockito.mock(IOrderPersistence.class);
        orderService = new OrderService(orderPersistenceMock);
        itemQuantities = new HashMap<>();

        pizza = new Food("pizza","1",5);
        burger = new Food("burger","2",10);
        fries = new Food("fries","3",4);

        itemQuantities.put(pizza,2);
        itemQuantities.put(burger,1);
        itemQuantities.put(fries,3);

        LocalTime time = LocalTime.now();
        LocalDate date = LocalDate.now();

        order = new Order();
        order.setOrderDate(date);
        order.setOrderTime(time);
        order.setMailId("email");
    }

    @AfterEach
    public void reset(){
        Mockito.clearInvocations(orderPersistenceMock);
    }

    @Test
    public void createOrderFromItemQuantities() {

        Order newOrder = orderService.createOrderFromItemQuantities("email",itemQuantities);

        assertEquals(2,newOrder.getOrderItemQuantities().get(pizza));
        assertEquals(1,newOrder.getOrderItemQuantities().get(burger));
        assertEquals(3,newOrder.getOrderItemQuantities().get(fries));
        assertEquals("email",newOrder.getMailId());

        assertNotNull(newOrder.getOrderDate());
        assertNotNull(newOrder.getOrderDate());
        assertNull(newOrder.getOrderId());
    }

    @Test
    public void createOrderFromItemQuantitiesEmptyItems() {

        Order newOrder = orderService.createOrderFromItemQuantities("email", new HashMap<IItem,Integer>());

        assertNull(newOrder);
    }

    @Test
    public void createOrderFromItemQuantitiesNullItems() {

        Order newOrder = orderService.createOrderFromItemQuantities("email",null);

        assertNull(newOrder);
    }

    @Test
    public void saveOrder() throws SQLException {

        orderService.saveOrder(order);
        Mockito.verify(orderPersistenceMock,times(1)).saveOrder(order);

    }

    @Test
    public void getOrder() throws SQLException {

        Mockito.when(orderPersistenceMock.loadOrder(10)).thenReturn(new Order());
        assertNotNull(orderService.getOrder(10));
        Mockito.verify(orderPersistenceMock,times(1)).loadOrder(10);

    }

    @Test
    public void getOrderDoesntExist() throws SQLException {

        Mockito.when(orderPersistenceMock.loadOrder(10)).thenReturn(null);
        assertNull(orderService.getOrder(10));
        Mockito.verify(orderPersistenceMock,times(1)).loadOrder(10);

    }
    @Test
    public void getAllOrdersForUser() throws SQLException {

        ArrayList<Order> orders = new ArrayList<>();
        orders.add(order);

        Mockito.when(orderPersistenceMock.loadAllOrders("email")).thenReturn(orders);

        assertEquals(orders,orderService.getAllOrdersForUser("email"));
        Mockito.verify(orderPersistenceMock,times(1)).loadAllOrders("email");

    }

    @Test
    public void getAllOrdersForUserButUserHasNoOrders() throws SQLException {

        Mockito.when(orderPersistenceMock.loadAllOrders("email")).thenReturn(null);

        assertTrue(orderService.getAllOrdersForUser("email").isEmpty());
        Mockito.verify(orderPersistenceMock,times(1)).loadAllOrders("email");

    }
}

