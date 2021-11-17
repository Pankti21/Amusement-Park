package com.team5.HAPark.paymentsystem;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class PaymentController {

    @RequestMapping("/payment")
    public ModelAndView paymentmethod(){
    ModelAndView m = new ModelAndView();
    m.setViewName("PaymentUI");
    return m;
}


    @RequestMapping(value = "/payment/add",method = RequestMethod.POST)
    public String addpayment(@RequestBody Payment py) {
        return py.Validate();
    }



}
