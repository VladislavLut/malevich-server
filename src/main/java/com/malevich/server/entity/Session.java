package com.malevich.server.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.io.Serializable;

import static com.malevich.server.entity.Session.TABLE_NAME;

@Entity
@Table(name = TABLE_NAME)
public class Session implements Serializable {
    public static final String TABLE_NAME = "sessions";

    public static final String USER_ID_COLUMN = "user_id";
    public static final String SID_COLUMN = "sid";

    @Id
    @Column(name = SID_COLUMN, unique = true)
    private String sid;

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "user_id")
    @JsonIdentityReference(alwaysAsId = true)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = USER_ID_COLUMN, nullable = false, unique = true)
    private User user;

    protected Session() {
    }

    public Session(User user, String sid) {
        this.user = user;
        this.sid = sid;
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
}
