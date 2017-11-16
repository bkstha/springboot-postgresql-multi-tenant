package com.journal.app.models.enums;


public enum PrintType {
    N("None"), I3("3 Inch"), I5("5 Inch");

    private String value;

    PrintType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
