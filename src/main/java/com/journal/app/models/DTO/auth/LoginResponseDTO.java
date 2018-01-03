package com.journal.app.models.DTO.auth;

import com.journal.app.models.DTO.CompanyDTO;
import com.journal.app.models.domain.Authority;

import java.util.List;

public class LoginResponseDTO {

    private String username;

    private String email;

    private Long ucid;

    private Long uid;

    private List<String> errors;

    private String token;

    private Boolean toCompanyList = false;

    private List<String> roles;

    private CompanyDTO company;


    //getter-setter


    public CompanyDTO getCompany() {
        return company;
    }

    public void setCompany(CompanyDTO company) {
        this.company = company;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

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

}

