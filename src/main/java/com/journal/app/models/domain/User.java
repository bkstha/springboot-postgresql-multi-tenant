package com.journal.app.models.domain;

import com.journal.app.models.enums.AlertType;
import com.journal.app.models.enums.Gender;
import com.journal.app.models.enums.UserDate;
import org.hibernate.Session;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by lakhey on 15/08/2017.
 */
@Entity
@Table(name = "users")
@GenericGenerator(
        name = "idgen",
        strategy = "com.journal.app.models.UseIdOrGenerate",
        parameters = {
                @org.hibernate.annotations.Parameter(name = "sequence_name", value = "seq_public"),
                @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                @org.hibernate.annotations.Parameter(name = "increment_size", value = "1"),
                @org.hibernate.annotations.Parameter(name = "optimizer", value = "pooled-lo")
        }
)
public class User extends AbstractModel {

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "password", length = 100)
    private String password;

    @Column(name = "username", length = 20)
    private String username;

    @Column(name = "user_date", nullable = false)
    @Enumerated(EnumType.STRING)
    private UserDate userDate= UserDate.INT;

    @Column(name = "email")
    private String email;

    @Column(name = "contact_number", length = 15)
    private String contactNumber;

    @Column(name = "gender", length = 1)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "status")
    private Boolean loginAccess;

    @Column(length = 40, unique = true)
    private String token;

    @Column(name = "token_expire", columnDefinition = "timestamp with time zone")
    private Calendar tokenExpire;

    @ManyToOne
    @JoinColumn(name = "country", foreignKey = @ForeignKey(name = "fk_country_id"))
    private Country country;

    @Column(name = "alert_type")
    @Enumerated(EnumType.STRING)
    private AlertType alertType;

    @OneToMany(mappedBy = "user")
    private List<Log> logList;

    @OneToMany(mappedBy = "user")
    private List<UserCompany> userCompanyList;

    @Column(name = "LAST_PASSWORD_RESET_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Date lastPasswordResetDate;


    //getter-setter

    public Date getLastPasswordResetDate() {
        return lastPasswordResetDate;
    }

    public void setLastPasswordResetDate(Date lastPasswordResetDate) {
        this.lastPasswordResetDate = lastPasswordResetDate;
    }

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
