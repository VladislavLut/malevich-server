package com.malevich.server.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

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

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne
    @JoinColumn(name = TABLE_ID_COLUMN, nullable = false)
    private TableItem tableItem;

    @NotNull
    @Column(name = DATE_COLUMN)
    private Date date;

    @NotNull
    @Column(name = TIME_COLUMN)
    private Time time;

    @NotNull
    @Column(name = PHONE_COLUMN)
    private String phone;

    @NotNull
    @Column(name = NAME_COLUMN)
    private String name;

    @Column(name = COMMENT_COLUMN)
    private String comment;

    protected Reservation() {
    }

    public Reservation(int id) {
        this.id = -1;
        tableItem = new TableItem(-1);
        date = new Date(0);
        time = new Time(0);
        phone = "";
        name = "";
        comment = "";
    }

    public Reservation(TableItem tableItem, String date, String time, String name, String phone, String comment) {
        this.tableItem = tableItem;
        this.date = date == null ? new Date(0) : Date.valueOf(date);
        this.time = time == null ? new Time(0) : Time.valueOf(time);
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
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
