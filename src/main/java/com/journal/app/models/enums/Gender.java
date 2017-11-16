package com.journal.app.models.enums;

public enum Gender {
    M("Male"), F("Female"), O("Others");

    private String value;

    Gender(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
