package com.journal.app.models.enums;

/**
 * Created by akalico on 24/09/2016.
 */
public enum UserDate {
    INT("International"), NP("Nepali");

    private String value;

    UserDate(String value){
        this.value=value;
    }
    public String getValue() {
        return value;
    }

}
