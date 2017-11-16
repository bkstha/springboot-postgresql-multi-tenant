package com.journal.app.models.enums;

public enum AppMode {
    N("Online"), F("Offline");

    private String value;

    AppMode(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
