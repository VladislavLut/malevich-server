package com.malevich.server.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = Reservation.TABLE_NAME)
public class Reservation implements Serializable {

    public static final String TABLE_NAME = "reserved";

    public static final String ID_COLUMN = "id";
    public static final String TABLE_ID_COLUMN = "table_id";
    public static final String DATE_COLUMN = "date";
    public static final String TIME_COLUMN = "time";
    public static final String PHONE_COLUMN = "phone";
    public static final String NAME_COLUMN = "name";
    public static final String COMMENT_COLUMN = "comment";

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = ID_COLUMN)
    private int id;

    @ManyToOne
    @JoinColumn(name = TABLE_ID_COLUMN, nullable = false)
    private TableItem tableItem;

    @NotNull
    @Column(name = DATE_COLUMN)
    private String date;

    @NotNull
    @Column(name = TIME_COLUMN)
    private String time;

    @NotNull
    @Column(name = PHONE_COLUMN)
    private String phone;

    @NotNull
    @Column(name = PHONE_COLUMN)
    private String name;

    @Column(name = COMMENT_COLUMN)
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
