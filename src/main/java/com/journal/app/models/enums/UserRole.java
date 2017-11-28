package com.journal.app.models.enums;

public enum UserRole {
    Z("Super Admin"), AA("App Admin"), C("Client"), A("Admin"), R("Reporting"), P("Parties");

    private String value;

    UserRole(String value){
        this.value=value;
    }

    public String getValue() {
        return value;
    }

}
