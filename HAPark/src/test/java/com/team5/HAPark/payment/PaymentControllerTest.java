package com.team5.HAPark.payment;

import org.junit.Assert;
import org.junit.Test;

public class PaymentControllerTest {

    @Test
    public void Testaddpayment() {
        PaymentController pc = new PaymentController();
        String resultMessage = PaymentError.SUCCESSFUL.getResultMessage();
        Assert.assertEquals(resultMessage, pc.addpayment(new Payment("credit", "3574747389293745", "10-03-2021", "", "234")));
    }
}