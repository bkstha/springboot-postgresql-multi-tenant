package com.journal.app.models.enums;


public enum Action {
    C("Create"), U("Update"), D("Delete"), LO("Login"), LG("logout");
    private String value;
    public String value() { return value; }
    Action(String value) {
        this.value = value;
    }
}
