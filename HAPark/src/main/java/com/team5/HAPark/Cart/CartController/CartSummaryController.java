package com.team5.HAPark.Cart.CartController;

import com.team5.HAPark.Cart.model.ICartAbstractFactory;
import com.team5.HAPark.Cart.model.CartFactoryProducer;
import com.team5.HAPark.Cart.model.ICartSummary;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.sql.SQLException;

@org.springframework.stereotype.Controller
    public class CartSummaryController {

        //@Autowired
        ICartAbstractFactory iCartFactory = CartFactoryProducer.getFactory(false);
        ICartSummary iCartSummary = iCartFactory.getCart("CartSummary");


        @GetMapping("/cartsummary")
        public String allCartItems(Model model) throws SQLException {
            model.addAttribute("cartSummary", iCartSummary);
            return "CartSummary";
        }
}
