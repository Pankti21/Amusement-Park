package com.team5.HAPark.Cart;

import com.team5.HAPark.Food.Food;
import com.team5.HAPark.Food.FoodOrderItem;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@SpringJUnitWebConfig
class CartSummaryTest {

        @Autowired
        CartSummary cartSummary;
        @Autowired
        MockHttpSession session;

        //https://newbedev.com/spring-test-session-scope-bean-using-junit


        @Test
        void getCartNewSession() {

            CartSummary cart1 = cartSummary;

            CartSummary cart2 = cartSummary;

            assertNotEquals(cart1,cart2);
        }

        @Test
        void getCartSameSession() {

            System.out.println(session.getId());

            CartSummary cart1 = cartSummary;
            FoodOrderItem foodOrderItem = new FoodOrderItem(new Food("pizza","1",5),1);
            cart1.addFoodToCart(foodOrderItem);

            System.out.println(session.getId());

            CartSummary cart2 = cartSummary;

            assertTrue(cart2.getFood().contains(foodOrderItem));
            assertEquals(cart1,cart2);
        }
}