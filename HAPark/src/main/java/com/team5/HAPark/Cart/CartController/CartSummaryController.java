package com.team5.HAPark.Cart.CartController;

import com.team5.HAPark.Cart.model.ICartFactory;
import com.team5.HAPark.Cart.model.FactoryProducer;
import com.team5.HAPark.Cart.model.ICartSummary;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.sql.SQLException;

@org.springframework.stereotype.Controller
    public class CartSummaryController {

        //@Autowired
        ICartFactory iCartFactory = FactoryProducer.getFactory(false);
        ICartSummary iCartSummary = iCartFactory.getCart("CartSummary");


        @GetMapping("/cartsummary")
        public String allCartItems(Model model) throws SQLException {
            model.addAttribute("cartSummary", iCartSummary);
            return "CartSummary";
        }
}
