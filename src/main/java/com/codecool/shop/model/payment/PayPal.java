package com.codecool.shop.model.payment;

import com.codecool.shop.model.payment.Payment;

import java.util.HashMap;

public class PayPal extends Payment {
    public PayPal(HashMap<String, String> details) {
        super(details);
    }
}
