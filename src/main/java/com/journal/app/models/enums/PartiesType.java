package com.journal.app.models.enums;

/**
 * Created by akalico on 24/09/2016.
 */
public enum PartiesType {
    D("Debtor"), C("Creditor");

    private String value;

    PartiesType(String value){
        this.value=value;
    }
    public String getValue() {
        return value;
    }

}
