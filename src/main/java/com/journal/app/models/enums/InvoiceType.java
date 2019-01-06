package com.journal.app.models.enums;


public enum InvoiceType {
    I("Inventory"), S("Service"), R("Restaurant");

    private String value;

    InvoiceType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
