package com.malevich.server.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Time;

import static com.malevich.server.entity.Session.TABLE_NAME;

@Entity
@Table(name = TABLE_NAME)
public class Session implements Serializable {
    public static final String TABLE_NAME = "sessions";

    public static final String USER_ID_COLUMN = "user_id";
    public static final String SID_COLUMN = "sid";
    public static final String LOGGED_IN_COLUMN = "logged_in";
    public static final String LAST_ACTIVITY_TIME_COLUMN = "last_activity";
    public static final String SESSION_START_TIME_COLUMN = "start_time";

    @Id
    @Column(name = SID_COLUMN, unique = true)
    private String sid;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = USER_ID_COLUMN, unique = true)
    private User user;

    @Column(name = LOGGED_IN_COLUMN, nullable = false)
    private Boolean loggedIn;

    @Column(name = LAST_ACTIVITY_TIME_COLUMN)
    private Time lastActivity;

    @Column(name = SESSION_START_TIME_COLUMN)
    private Time startTime;

    protected Session() {
    }

    public Session(String sid) {
        this(null, sid);
    }

    public Session(User user, String sid) {
        this(user, sid, false);
    }

    public Session(User user, String sid, Boolean loggedIn) {
        this.user = user;
        this.sid = sid;
        this.loggedIn = loggedIn;
        lastActivity = new Time(System.currentTimeMillis());
        startTime = new Time(System.currentTimeMillis());
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public Boolean getLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(Boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public Time getLastActivity() {
        return lastActivity;
    }

    public void setLastActivity(Time lastActivity) {
        this.lastActivity = lastActivity;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }
}
