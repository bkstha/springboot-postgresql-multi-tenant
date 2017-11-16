package com.journal.app.models.enums;

/**
 * Created by akalico on 24/09/2016.
 */
public enum BloodGroup {
    aPos("A+"), aNeg("A-"), bPos("B+"),bNeg("B-"), abPos("AB+"),abNeg("AB-"), oPos("O+"),oNeg("O-");

    private String value;

    BloodGroup(String value){
        this.value=value;
    }
    public String getValue() {
        return value;
    }

}
