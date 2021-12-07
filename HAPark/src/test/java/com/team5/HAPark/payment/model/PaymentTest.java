package com.team5.HAPark.payment.model;

import com.team5.HAPark.payment.model.Payment;
import com.team5.HAPark.payment.model.PaymentError;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PaymentTest {

    @Test
    public void validate() {
        Payment p = new Payment("credit", "3574747389293745", "10-03-2021", "", "234");
        assertEquals(PaymentError.SUCCESSFUL, p.Validate());
    }

}
