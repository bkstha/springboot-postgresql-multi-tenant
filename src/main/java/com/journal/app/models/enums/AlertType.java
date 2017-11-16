package com.journal.app.models.enums;

public enum AlertType {
    EA("Email Activation"), MA("Mobile Activation"), RP("Reset Password"), B("Blocked");
    private String value;

    public String value() { return value; }

    AlertType(String value) {
        this.value=value;
    }
}
