package paymentsystem;

import com.team5.HAPark.paymentsystem.Payment;
import com.team5.HAPark.paymentsystem.PaymentController;
import org.junit.Assert;
import org.junit.Test;

public class PaymentControllerTest {
    @Test
    public void Testaddpayment() {
        PaymentController pc = new PaymentController();
        Assert.assertEquals("Success", pc.addpayment(new Payment("credit", "3574747389293745", "10-03-2021", "", "234")));

    }
}
