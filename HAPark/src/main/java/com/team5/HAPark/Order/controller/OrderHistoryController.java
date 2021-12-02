package com.team5.HAPark.Order.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class OrderHistoryController {

    @GetMapping(value = "/orders")
    public String orderHistoryPage(){
        return "OrderHistory";
    }

}
