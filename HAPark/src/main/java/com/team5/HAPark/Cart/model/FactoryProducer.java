package com.team5.HAPark.Cart.model;

public class FactoryProducer {
    public static ICartFactory getFactory(boolean cart) {
        return new CartFactory();
    }
}
