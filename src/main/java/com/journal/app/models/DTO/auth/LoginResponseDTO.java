package com.journal.app.models.DTO.auth;

import com.journal.app.models.DTO.CompanyDTO;
import com.journal.app.models.DTO.UserDTO;
import com.journal.app.models.DTO.UsersCompanyDTO;

import java.util.List;

public class LoginResponseDTO {

    private UserDTO user;

//    private List<String> errors;

    private String token;

//    private Boolean toCompanyList = false;

    //getter-setter

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

//    public List<String> getErrors() {
//        return errors;
//    }
//
//    public void setErrors(List<String> errors) {
//        this.errors = errors;
//    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

//    public Boolean getToCompanyList() {
//        return toCompanyList;
//    }
//
//    public void setToCompanyList(Boolean toCompanyList) {
//        this.toCompanyList = toCompanyList;
//    }
}

