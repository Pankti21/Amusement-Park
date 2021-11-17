package paymentsystem;

import com.team5.HAPark.paymentsystem.Payment;
import org.junit.Test;
import org.junit.*;

public class PaymentTest {
    @Test
    public void TestValidate() {
        Payment p = new Payment("credit", "3574747389293745", "10-03-2021", "", "234");
        Assert.assertEquals("Success", p.Validate());
    }
}
