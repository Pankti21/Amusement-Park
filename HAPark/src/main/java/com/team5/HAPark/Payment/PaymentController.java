package com.team5.HAPark.Payment;

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
