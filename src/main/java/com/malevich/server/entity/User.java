package com.malevich.server.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.malevich.server.utils.UserType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = User.TABLE_NAME)
public class User implements Serializable {

    public static final String TABLE_NAME = "users";

    public static final String ID_COLUMN = "id";
    public static final String TYPE_COLUMN = "type";
    public static final String LOGIN_COLUMN = "login";
    public static final String PASSWORD_COLUMN = "password";
    public static final String NAME_COLUMN = "name";
    public static final String BIRTH_DAY_COLUMN = "birth_day";

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = ID_COLUMN)
    private int id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = TYPE_COLUMN)
    private UserType type;

    @NotNull
    @Column(name = LOGIN_COLUMN, unique = true)
    private String login;

    @JsonIgnore
    @JsonDeserialize
    @NotNull
    @Column(name = PASSWORD_COLUMN)
    private String password;

    @NotNull
    @Column(name = NAME_COLUMN)
    private String name;


    @Column(name = BIRTH_DAY_COLUMN)
    private Date birthDay;

    @JsonIgnore
    @OneToMany(mappedBy = "kitchener", cascade = CascadeType.ALL)
    private List<OrderedDish> orderedDishes;

    protected User() {
    }

    public User(int id) {
        this.id = -1;
        type = UserType.NULL;
        login = "";
        password = "";
        name = "";
        birthDay = new Date(0);
    }

    public User(UserType type, String login, String password, String name, String birthDay) {
        this.type = type;
        this.login = login;
        this.password = password;
        this.name = name;
        this.birthDay = Date.valueOf(birthDay);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
}