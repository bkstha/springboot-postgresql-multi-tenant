package com.journal.app.models.enums;


public enum ACCOUNT {
    A("assets"), L("liabilities"), I("income"),E("expenses"), P("parties");
    private String value;
    public String value() { return value; }
    ACCOUNT(String value) {
        this.value = value;
    }
}
