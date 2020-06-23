package com.singlestone.contactdemo.contactdemo.exceptions;

public class ContactNotFoundException extends RuntimeException {

    public ContactNotFoundException(String message) {
        super(message);
    }
}
