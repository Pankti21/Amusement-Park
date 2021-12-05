package com.team5.HAPark.payment.controller;

import com.team5.HAPark.payment.model.Payment;
import com.team5.HAPark.payment.model.PaymentError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class PaymentController {

    @RequestMapping("/payment")
    public ModelAndView paymentmethod(){
        ModelAndView m = new ModelAndView();
        m.setViewName("Payment");
        return m;
    }

    @RequestMapping(value = "/payment/add",method = RequestMethod.POST)
    public String addpayment(@RequestBody Payment py) {
        PaymentError paymentError = py.Validate();
        return paymentError.getResultMessage();
    }
}
