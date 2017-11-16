package com.journal.app.models.domain;

import com.journal.app.models.enums.Action;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;

@Entity
@Table(name = "logs")
public class Log implements Serializable{

    @Id
    @Column(name = "time", nullable = false, columnDefinition = "timestamp with time zone")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar time;

    @Id
    @ManyToOne
    @JoinColumn(name = "user_id", nullable=false, foreignKey = @ForeignKey(name = "fk_user_id"))
    private User user;

    @Column(name = "description", nullable = false, length = 1000)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name="action", nullable = false, length = 2)
    private Action action;

    @Column(name="ip_address", length = 45)
    private String ipAddress;

    @Column(name="url", length = 255)
    private String url;


    public Log(){}
    public Log(Calendar time, User user, String description, Action action, String ipAddress, String url) {
        this.time = time;
        this.user = user;
        this.description = description;
        this.action = action;
        this.ipAddress = ipAddress;
        this.url = url;
    }

    //geetter-setter


    public Calendar getTime() {
        return time;
    }

    public void setTime(Calendar time) {
        this.time = time;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
