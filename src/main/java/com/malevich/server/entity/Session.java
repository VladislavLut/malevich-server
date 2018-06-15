package com.malevich.server.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Time;

import static com.malevich.server.util.Strings.*;

@Entity
@Table(name = SESSIONS_TABLE_NAME)
public class Session implements Serializable {

    @Id
    @Column(name = SESSIONS_SID_COLUMN, unique = true)
    private String sid;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = SESSIONS_USER_ID_COLUMN, unique = true)
    private User user;

    @Column(name = SESSIONS_LOGGED_IN_COLUMN, nullable = false)
    private Boolean loggedIn;

    @Column(name = SESSIONS_LAST_ACTIVITY_TIME_COLUMN)
    private Time lastActivity;

    @Column(name = SESSIONS_SESSION_START_TIME_COLUMN)
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
        lastActivity = Time.valueOf(new Time(System.currentTimeMillis()).toString());
        startTime = lastActivity;
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
