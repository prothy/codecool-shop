package com.codecool.shop.model.payment;

import java.util.HashMap;

public class Payment {
    private boolean success;
    private HashMap<String, String> details;

    public Payment(HashMap<String, String> details) {
        this.success = false;
        this.details = details;
    }

    public boolean isSuccess() {
        return success;
    }

    public HashMap<String, String> getDetails() {
        return details;
    }

    public void setDetails(HashMap<String, String> details) {
        this.details = details;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}

