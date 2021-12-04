package com.team5.HAPark.cart.model;

public class CartFactoryProducer {
    public static ICartAbstractFactory getFactory(boolean cart) {
        return new CartAbstractFactory();
    }
}
