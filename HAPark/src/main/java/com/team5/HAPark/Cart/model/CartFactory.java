package com.team5.HAPark.Cart.model;

public class CartFactory implements ICartFactory {
    @Override
    public ICartSummary getCart(String cartType) {
        if(cartType.equalsIgnoreCase("CartSummary")) {
            return new CartSummary();
        }
        return null;
    }
}
