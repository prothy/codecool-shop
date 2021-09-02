package com.codecool.shop.model;

import java.util.HashMap;

public class Payment {
    private boolean success = false;
    private HashMap<String, String> details = new HashMap<>();

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

