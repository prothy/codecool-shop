package com.codecool.shop.model;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreditCard extends Payment{
    private static final String[] ACCEPTED_CARD_NETWORK = {"Visa", "Mastercard"};


    public CreditCard(HashMap<String, String> details) {
        super(details);
    }

    public void validateDetails() throws IllegalArgumentException{
        for (Map.Entry<String, String> entry : this.getDetails().entrySet()){
            switch (entry.getKey()) {
                case "name":
                    validateName(entry.getValue());
                    break;
                case "creditCardNumber":
                    validateCardNumber(entry.getValue());
                    break;
                case "expirationDate":
                    validateExpirationDate(entry.getValue());
                    break;
                case "cardNetwork":
                    validateCardNetwork(entry.getValue());
                    break;
                case "CVV":
                    validateCVV(entry.getValue());
                    break;
            }
        }
        this.setSuccess(true);
    }

    private void validateExpirationDate(String expirationDate) {
        String expirationDateRegex = "^0[1-9]|1[0-2]\\/?[0-9]{4}|[0-9]{2}$";
        Pattern pattern = Pattern.compile(expirationDateRegex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(expirationDate);
        if (!matcher.find()) {
            throw new IllegalArgumentException("Wrong expiration date format");
        }
    }

    private void validateCVV(String cvv) {
        String cvvRegex = "^[0-9][0-9][0-9]?[0-9]$";
        Pattern pattern = Pattern.compile(cvvRegex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(cvv);
        if (!matcher.find()) {
            throw new IllegalArgumentException("Wrong CVV code.");
        }
    }

    private void validateCardNetwork(String cardNetwork) {
        if (Arrays.asList(ACCEPTED_CARD_NETWORK).contains(cardNetwork)) {
            throw new IllegalArgumentException("There is no such as card network");
        }
    }

    private void validateCardNumber(String cardNumber) {
        String clearCardNumber = cardNumber.replace("-", "").replace(",", "");
        String cardNumberRegex = "^(?:4[0-9]{12}(?:[0-9]{3})?|[25][1-7][0-9]{14}|6(?:011|5[0-9][0-9])[0-9]{12}|3[47][0-9]{13}|3(?:0[0-5]|[68][0-9])[0-9]{11}|(?:2131|1800|35\\d{3})\\d{11})$";
        Pattern pattern = Pattern.compile(cardNumberRegex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(clearCardNumber);
        if (!matcher.find()) {
            throw new IllegalArgumentException("Wrong card number");
        }
    }

    private void validateName(String name) {
        if (name.equals("")) {
            throw new IllegalArgumentException("There is no name.");
        }
    }


}
