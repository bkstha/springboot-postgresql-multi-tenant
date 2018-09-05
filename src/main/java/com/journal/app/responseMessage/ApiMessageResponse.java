package com.journal.app.responseMessage;

public class ApiMessageResponse {
    private String message;

    public ApiMessageResponse(String message){
        this.message=message;
    }

    public ApiMessageResponse(){

    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
