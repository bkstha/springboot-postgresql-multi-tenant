package com.journal.app.models.enums;

/**
 * Created by akalico on 24/09/2016.
 */
public enum FoodType {
    NV("Non-Veg"), V("Veg"), E("Egg");

    private String value;

    FoodType(String value){
        this.value=value;
    }
    public String getValue() {
        return value;
    }

}
