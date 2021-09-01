package com.codecool.shop.model;

import java.util.HashMap;

public class Payment {
    protected boolean success = false;
    protected HashMap<String, String> details = new HashMap<>();

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

