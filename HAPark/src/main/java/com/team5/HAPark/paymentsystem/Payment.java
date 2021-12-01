package com.team5.HAPark.paymentsystem;

public class Payment {
    private String PaymentType;
    private String CardNumber;
    private String DateMM;
    private String DateYY;
    private String SecurityCode;

    public Payment(String paymentType, String cardNumber, String dateMM, String dateYY, String securityCode) {
        PaymentType = paymentType;
        CardNumber = cardNumber;
        DateMM = dateMM;
        DateYY = dateYY;
        SecurityCode = securityCode;
    }

    public String getPaymentType() {
        return PaymentType;
    }

    public void setPaymentType(String paymentType) {
        PaymentType = paymentType;
    }

    public String getCardNumber() {
        return CardNumber;
    }

    public void setCardNumber(String cardNumber) {
        CardNumber = cardNumber;
    }

    public String getDateMM() {
        return DateMM;
    }

    public void setDateMM(String dateMM) {
        DateMM = dateMM;
    }

    public String getDateYY() {
        return DateYY;
    }

    public void setDateYY(String dateYY) {
        DateYY = dateYY;
    }

    public String getSecurityCode() {
        return SecurityCode;
    }

    public void setSecurityCode(String securityCode) {
        SecurityCode = securityCode;
    }

    public PaymentError Validate() {
        PaymentError paymentError;

        if(PaymentType=="" || CardNumber=="" || DateMM=="" || SecurityCode=="")
        {
            paymentError = PaymentError.EMPTYFIELD;
        }
        else{
            if(SecurityCode.length()!=3 || CardNumber.length()!=16)
            {
                paymentError = PaymentError.INVALIDNUMBERLENGTH;
            }
            else
            {
                if(SecurityCode.chars().allMatch( Character::isDigit)==false || CardNumber.chars().allMatch( Character::isDigit)==false)
                {
                    paymentError = PaymentError.INVALIDIGITFORMAT;
                }
                else {
                    paymentError = PaymentError.SUCCESSFUL;
                }
            }
        }
        return paymentError;
    }
}
