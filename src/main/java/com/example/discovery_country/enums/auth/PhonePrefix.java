package com.example.discovery_country.enums.auth;

import lombok.Getter;

@Getter
public enum PhonePrefix {
    PREFIX_99410("+99410"),
    PREFIX_99450("+99450"),
    PREFIX_99451("+99451"),
    PREFIX_99455("+99455"),
    PREFIX_99499("+99499"),
    PREFIX_99470("+99450"),
    PREFIX_99477("+99451");


    private final String prefix;

    PhonePrefix(String prefix) {
        this.prefix = prefix;
    }

    public static PhonePrefix fromPrefix(String prefix) {
        for (PhonePrefix phoneNumberPrefix : values()) {
            if (phoneNumberPrefix.getPrefix().equals(prefix)) {
                return phoneNumberPrefix;
            }
        }
        throw new IllegalArgumentException("Unknown phone number prefix: " + prefix);
    }
}
