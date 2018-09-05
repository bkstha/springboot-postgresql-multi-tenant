package com.journal.app.models.DTO.auth;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class RegisterRequestDTO {

    @NotNull(message = "Username is required.")
    @Size(max = 20, min = 5, message = "Username must be between 5-20 characters.")
    @NotEmpty(message = "Username is required.")
    private String username;

    @NotNull(message = "Email is required.")
    @Email(message = "Please enter valid Email address.")
    @Size(max = 70, message = "Email must not exceed more than 70 characters.")
    @NotEmpty(message = "Email is required.")
    private String email;

    @NotNull(message = "Password is required.")
    @Size(max = 50, message = "Password must not exceed more than 50 characters.")
    @NotEmpty(message = "Password is required.")
    private String password;

    @NotNull(message = "Repeat password is required.")
    @Size(max = 50, message = "Repeat password must not exceed more than 50 characters.")
    @NotEmpty(message = "Repeat password is required.")
    private String repeatPassword;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }

    @Override
    public String toString() {
        return "RegisterRequestDTO{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", repeatPassword='" + repeatPassword + '\'' +
                '}';
    }
}
