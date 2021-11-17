package com.team5.HAPark.Order;

import com.team5.HAPark.Food.Food;
import com.team5.HAPark.Food.FoodOrderItem;
import com.team5.HAPark.Order.DAO.IFoodOrderPersistence;
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

class FoodOrderServiceTest {

    private static FoodOrderService foodOrderService;
    private static IFoodOrderPersistence orderPersistenceMock;
    private static List<FoodOrderItem> orderItems;
    private static Food pizza;
    private static Food burger;
    private static Food fries;
    private static FoodOrderItem pizzaOrder;
    private static FoodOrderItem burgerOrder;
    private static FoodOrderItem friesOrder;
    private static FoodOrder order;

    @BeforeAll
    public static void init(){

        orderPersistenceMock = Mockito.mock(IFoodOrderPersistence.class);
        foodOrderService = new FoodOrderService(orderPersistenceMock);
        orderItems = new ArrayList<>();

        pizza = new Food("pizza","1",5);
        burger = new Food("burger","2",10);
        fries = new Food("fries","3",4);

        pizzaOrder = new FoodOrderItem(pizza,2);
        burgerOrder = new FoodOrderItem(burger,1);
        friesOrder = new FoodOrderItem(fries,3);

        orderItems.add(pizzaOrder);
        orderItems.add(burgerOrder);
        orderItems.add(friesOrder);

        LocalTime time = LocalTime.now();
        LocalDate date = LocalDate.now();

        order = new FoodOrder();
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

        FoodOrder newOrder = foodOrderService.createOrderFromItemQuantities("email", orderItems);
        List<FoodOrderItem> orderItems = newOrder.getOrderItems();

        assertTrue(orderItems.contains(pizzaOrder));
        assertTrue(orderItems.contains(burgerOrder));
        assertTrue(orderItems.contains(friesOrder));
    }

    @Test
    public void createOrderFromItemQuantitiesHasCorrectEmail() {

        FoodOrder newOrder = foodOrderService.createOrderFromItemQuantities("email", orderItems);
        List<FoodOrderItem> orderItems = newOrder.getOrderItems();

        assertEquals("email",newOrder.getMailId());

        assertNotNull(newOrder.getOrderDate());
        assertNotNull(newOrder.getOrderDate());
        assertNull(newOrder.getOrderId());
    }

    @Test
    public void createOrderFromItemQuantitiesHasDateTime() {

        FoodOrder newOrder = foodOrderService.createOrderFromItemQuantities("email", orderItems);
        List<FoodOrderItem> orderItems = newOrder.getOrderItems();

        assertNotNull(newOrder.getOrderDate());
        assertNotNull(newOrder.getOrderDate());
    }

    @Test
    public void createOrderFromItemQuantitiesHasOrderId() {

        FoodOrder newOrder = foodOrderService.createOrderFromItemQuantities("email", orderItems);
        List<FoodOrderItem> orderItems = newOrder.getOrderItems();

        assertNull(newOrder.getOrderId());
    }

    @Test
    public void createOrderFromItemQuantitiesEmptyItems() {

        FoodOrder newOrder = foodOrderService.createOrderFromItemQuantities("email", new ArrayList<>());

        assertNull(newOrder);
    }

    @Test
    public void createOrderFromItemQuantitiesNullItems() {

        FoodOrder newOrder = foodOrderService.createOrderFromItemQuantities("email",null);

        assertNull(newOrder);
    }

    @Test
    public void saveOrder() throws SQLException {

        foodOrderService.saveOrder(order);
        Mockito.verify(orderPersistenceMock,times(1)).saveOrder(order);

    }

    @Test
    public void getOrder() throws SQLException {

        Mockito.when(orderPersistenceMock.loadOrder(10)).thenReturn(new FoodOrder());
        assertNotNull(foodOrderService.getOrder(10));
        Mockito.verify(orderPersistenceMock,times(1)).loadOrder(10);

    }

    @Test
    public void getOrderDoesntExist() throws SQLException {

        Mockito.when(orderPersistenceMock.loadOrder(10)).thenReturn(null);
        assertNull(foodOrderService.getOrder(10));
        Mockito.verify(orderPersistenceMock,times(1)).loadOrder(10);

    }
    @Test
    public void getAllOrdersForUser() throws SQLException {

        ArrayList<FoodOrder> orders = new ArrayList<>();
        orders.add(order);

        Mockito.when(orderPersistenceMock.loadAllOrders("email")).thenReturn(orders);

        assertEquals(orders, foodOrderService.getAllOrdersForUser("email"));
        Mockito.verify(orderPersistenceMock,times(1)).loadAllOrders("email");

    }

    @Test
    public void getAllOrdersForUserButUserHasNoOrders() throws SQLException {

        Mockito.when(orderPersistenceMock.loadAllOrders("email")).thenReturn(null);

        assertTrue(foodOrderService.getAllOrdersForUser("email").isEmpty());
        Mockito.verify(orderPersistenceMock,times(1)).loadAllOrders("email");

    }
}

