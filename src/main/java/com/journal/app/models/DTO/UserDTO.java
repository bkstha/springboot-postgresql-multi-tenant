package com.journal.app.models.DTO;

import com.journal.app.models.domain.Country;
import com.journal.app.models.domain.Log;
import com.journal.app.models.domain.UserCompany;
import com.journal.app.models.enums.AlertType;
import com.journal.app.models.enums.Gender;
import com.journal.app.models.enums.UserDate;

import java.util.Calendar;
import java.util.List;

public class UserDTO {

    private String name;

    private String password;

    private String username;

    private UserDate userDate= UserDate.INT;

    private String email;

    private String contactNumber;

    private Gender gender;

    private Boolean loginAccess;

    private String token;

    private Calendar tokenExpire;

    private Country country;

    private AlertType alertType;

    private List<Log> logList;

    private List<UserCompany> userCompanyList;

    //getter-setter


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UserDate getUserDate() {
        return userDate;
    }

    public void setUserDate(UserDate userDate) {
        this.userDate = userDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Boolean getLoginAccess() {
        return loginAccess;
    }

    public void setLoginAccess(Boolean loginAccess) {
        this.loginAccess = loginAccess;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Calendar getTokenExpire() {
        return tokenExpire;
    }

    public void setTokenExpire(Calendar tokenExpire) {
        this.tokenExpire = tokenExpire;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public AlertType getAlertType() {
        return alertType;
    }

    public void setAlertType(AlertType alertType) {
        this.alertType = alertType;
    }

    public List<Log> getLogList() {
        return logList;
    }

    public void setLogList(List<Log> logList) {
        this.logList = logList;
    }

    public List<UserCompany> getUserCompanyList() {
        return userCompanyList;
    }

    public void setUserCompanyList(List<UserCompany> userCompanyList) {
        this.userCompanyList = userCompanyList;
    }
}
