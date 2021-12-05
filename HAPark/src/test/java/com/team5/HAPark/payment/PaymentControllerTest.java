package com.team5.HAPark.payment;

import com.team5.HAPark.payment.controller.PaymentController;
import com.team5.HAPark.payment.model.Payment;
import com.team5.HAPark.payment.model.PaymentError;
import org.junit.Assert;
import org.junit.jupiter.api.Test;


public class PaymentControllerTest {

    @Test
    void addpayment() {
        PaymentController pc = new PaymentController();
        String resultMessage = PaymentError.SUCCESSFUL.getResultMessage();
        Assert.assertEquals(resultMessage, pc.addpayment(new Payment("credit", "3574747389293745", "10-03-2021", "", "234")));
    }

}