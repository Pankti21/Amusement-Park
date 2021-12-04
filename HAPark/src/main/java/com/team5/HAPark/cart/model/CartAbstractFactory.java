package com.team5.HAPark.cart.model;

public class CartAbstractFactory implements ICartAbstractFactory {
    @Override
    public ICartSummary getCart(String cartType) {
        if(cartType.equalsIgnoreCase("CartSummary")) {
            return new CartSummary();
        }
        return null;
    }
}
