package com.team5.HAPark.Cart.model;

public class CartAbstractFactory implements ICartAbstractFactory {
    @Override
    public ICartSummary getCart(String cartType) {
        if(cartType.equalsIgnoreCase("CartSummary")) {
            return new CartSummary();
        }
        return null;
    }
}
