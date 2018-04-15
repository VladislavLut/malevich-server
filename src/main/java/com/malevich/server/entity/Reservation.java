package com.malevich.server.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "reserved")
public class Reservation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "table_id", nullable = false)
    private TableItem tableItem;

    @NotNull
    @Column(name = "date")
    private String date;

    @NotNull
    @Column(name = "time")
    private String time;

    @NotNull
    @Column(name = "phone")
    private String phone;

    @NotNull
    @Column(name = "name")
    private String name;

    @Column(name = "comment")
    private String comment;

    protected Reservation() {
    }

    public Reservation(TableItem tableItem, String date, String time, String name, String phone, String comment) {
        this.tableItem = tableItem;
        this.date = date == null ? "" : date;
        this.time = time == null ? "" : time;
        this.name = name == null ? "" : name;
        this.phone = phone == null ? "" : phone;
        this.comment = comment == null ? "" : comment;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TableItem getTableItem() {
        return tableItem;
    }

    public void setTableItem(TableItem tableItem) {
        this.tableItem = tableItem;
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
