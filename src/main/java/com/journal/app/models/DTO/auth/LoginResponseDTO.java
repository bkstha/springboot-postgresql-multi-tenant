package com.journal.app.models.DTO.auth;

import com.journal.app.models.DTO.users.UserDTO;

public class LoginResponseDTO {

    private UserDTO user;
    private String token;

    //getter-setter

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}

