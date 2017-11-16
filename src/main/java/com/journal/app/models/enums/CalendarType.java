package com.journal.app.models.enums;

public enum CalendarType {
    IN("International"), NP("Nepali");
    private String value;
    CalendarType(String value) {
        this.value = value;
    }
    public String getValue() {
        return value;
    }
}
