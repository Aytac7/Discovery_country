package com.example.discovery_country.exception;

public class DuplicatePhoneNumberException extends RuntimeException {

    public DuplicatePhoneNumberException(String message){
        super(message);
    }
}