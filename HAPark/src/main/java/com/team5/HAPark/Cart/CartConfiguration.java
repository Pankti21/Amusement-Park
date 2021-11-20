package com.team5.HAPark.Cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class CartConfiguration {

    @Autowired private CartSummary cart;

    @Bean
    @Scope(value="session")
    public CartSummary getCart() {
        return cart;
    }
}
