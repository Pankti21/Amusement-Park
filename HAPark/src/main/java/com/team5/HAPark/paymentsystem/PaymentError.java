package com.team5.HAPark.paymentsystem;

public enum PaymentError {
    SUCCESSFUL("success"),
    EMPTYFIELD("empty field"),
    INVALIDNUMBERLENGTH("number of digits is incorrect for card number or security code"),
    INVALIDIGITFORMAT("CVV and Card number should be numeric");


    private String result;

    public String getResultMessage() {
        return result;
    }

    private PaymentError(String result) {
        this.result = result;
    }
}
