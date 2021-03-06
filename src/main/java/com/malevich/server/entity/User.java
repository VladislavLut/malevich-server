package com.malevich.server.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.malevich.server.enums.UserType;
import com.malevich.server.view.Views;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import static com.malevich.server.util.Strings.*;

@Entity
@Table(name = USERS_TABLE_NAME)
public class User implements Serializable {

    @JsonView(Views.Public.class)
    @Id
    @Column(name = USERS_ID_COLUMN)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @JsonView(Views.Public.class)
    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(name = USERS_TYPE_COLUMN)
    private UserType type;

    @JsonView(Views.Public.class)
    @NotNull
    @Column(name = USERS_LOGIN_COLUMN, unique = true)
    private String login;

    @NotNull
    @Column(name = USERS_PASSWORD_COLUMN)
    private String password;

    @JsonView(Views.Public.class)
    @NotNull
    @Column(name = USERS_NAME_COLUMN)
    private String name;

    @JsonView(Views.Public.class)
    @Column(name = USERS_BIRTH_DAY_COLUMN)
    private Date birthDay;

    @JsonView(Views.Public.class)
    @Column(name = USERS_PHONE_COLUMN)
    private String phone;

    @JsonIgnore
    @OneToMany(mappedBy = "kitchener", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<OrderedDish> orderedDishes;

    @JsonIgnore
    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Session session;

    protected User() {
    }

    public User(Integer id) {
        this.id = id;
    }

    public User(UserType type, String login, String password, String name, String birthDay) {
        this(type, login, password, name, birthDay, null);
    }

    public User(UserType type, String login, String password, String name, String birthDay, String phone) {
        this.type = type;
        this.login = login;
        this.password = password;
        this.name = name;
        this.birthDay = Date.valueOf(birthDay);
        this.phone = phone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public List<OrderedDish> getOrderedDishes() {
        return orderedDishes;
    }

    public void setOrderedDishes(List<OrderedDish> orderedDishes) {
        this.orderedDishes = orderedDishes;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }
}