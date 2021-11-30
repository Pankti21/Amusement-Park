package com.team5.HAPark.Cart.model;

public class CartFactoryProducer {
    public static ICartAbstractFactory getFactory(boolean cart) {
        return new CartAbstractFactory();
    }
}
