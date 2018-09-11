package com.journal.app.models.DTO;


import com.journal.app.models.enums.Gender;
import com.journal.app.models.enums.UserDate;

import java.util.List;

public class UserDTO {

    private Long id;

    private String name;

    private String username;

    private UserDate userDate= UserDate.INT;

    private String email;

    private String contactNumber;

    private Gender gender;

    private List<UsersCompanyDTO> companyList;

    //getter-setter


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public List<UsersCompanyDTO> getCompanyList() {
        return companyList;
    }

    public void setCompanyList(List<UsersCompanyDTO> companyList) {
        this.companyList = companyList;
    }
}
