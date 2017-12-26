package com.journal.app.models.DTO.auth;

import java.util.List;

public class LoginResponseDTO {

    private String username;

    private String email;

    private Long ucid;

    private List<String> errors;

    private String token;

    private Boolean toCompanyList = false;

    private String schema;


    //getter-setter

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getUcid() {
        return ucid;
    }

    public void setUcid(Long ucid) {
        this.ucid = ucid;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Boolean getToCompanyList() {
        return toCompanyList;
    }

    public void setToCompanyList(Boolean toCompanyList) {
        this.toCompanyList = toCompanyList;
    }

    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }
}
