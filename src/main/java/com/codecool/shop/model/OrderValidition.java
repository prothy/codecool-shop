package com.codecool.shop.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OrderValidition {

    private String name;
    private String email;

    public OrderValidition(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public boolean isEmailValid() {

        Pattern VALID_EMAIL_ADDRESS_REGEX =
                Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(this.email);

        return matcher.find();

    }

    public boolean isNameValid() {

        if (this.name != null && this.name.matches("^[\\p{L} .'-]+$")) {
            return true;
        }

        return false;
    }

    public boolean everythingIsValid() {

        if (isEmailValid() && isNameValid()) {
            return true;
        }
        else {
            return false;
        }

    }

}
