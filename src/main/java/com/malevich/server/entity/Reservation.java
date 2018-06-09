package com.malevich.server.entity;

import com.fasterxml.jackson.annotation.*;
import com.malevich.server.view.Views;

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

    @JsonView(Views.Public.class)
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = ID_COLUMN)
    private Integer id;

    @JsonView(Views.Public.class)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne
    @JoinColumn(name = TABLE_ID_COLUMN, nullable = false)
    private TableItem tableItem;

    @JsonView(Views.Public.class)
    @NotNull
    @Column(name = DATE_COLUMN)
    private Date date;

    @JsonView(Views.Public.class)
    @NotNull
    @Column(name = TIME_COLUMN)
    private Time time;

    @JsonView(Views.Public.class)
    @NotNull
    @Column(name = PHONE_COLUMN)
    private String phone;

    @JsonView(Views.Public.class)
    @NotNull
    @Column(name = NAME_COLUMN)
    private String name;

    @JsonView(Views.Public.class)
    @Column(name = COMMENT_COLUMN)
    private String comment;

    protected Reservation() {
    }

    public Reservation(Integer id) {
        this.id = id;
    }

    public Reservation(Integer tableId, String date, String time, String name, String phone, String comment) {
        this(new TableItem(tableId), date, time, name, phone, comment);
    }

    public Reservation(TableItem tableItem, String date, String time, String name, String phone, String comment) {
        this.tableItem = tableItem;
        this.date = Date.valueOf(date);
        this.time = Time.valueOf(time);
        this.name = name;
        this.phone = phone;
        this.comment = comment;
    }

    public Integer getId() {

        return id;
    }

    public void setId(Integer id) {
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
