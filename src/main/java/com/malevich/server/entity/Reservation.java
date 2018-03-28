package com.malevich.server.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@javax.persistence.Table(name = "reserved")
public class Reservation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "table_id", nullable = false)
    private Table table;

    @Column(name = "date", nullable = false)
    private String date;

    @Column(name = "time", nullable = false)
    private String time;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "comment")
    private String comment;

    protected Reservation() {
    }

    public Reservation(Table table, String date, String time, String phone, String name, String comment) {
        this.table = table;
        this.date = date;
        this.time = time;
        this.phone = phone;
        this.name = name;
        this.comment = comment;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
