package com.malevich.server.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.malevich.server.utils.Status;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

import static com.malevich.server.entity.Order.TABLE_NAME;

@Entity
@Table(name = TABLE_NAME)
public class Order implements Serializable {

    public static final String TABLE_NAME = "orders";

    public static final String ID_COLUMN = "id";
    public static final String TABLE_ID_COLUMN = "table_id";
    public static final String STATUS_COLUMN = "status";
    public static final String DATE_COLUMN = "date";

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = ID_COLUMN)
    private int id;

    @ManyToOne
    @JoinColumn(name = TABLE_ID_COLUMN)
    private TableItem tableItem;

    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(name = STATUS_COLUMN)
    private Status status;

    @NotNull
    @Column(name = DATE_COLUMN)
    private String date;

    @JsonIgnore
    @OneToOne(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Comment comment;

    @JsonIgnore
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderedDish> orderedDishes;

    protected Order() {
    }

    public Order(TableItem tableItem, Status status, String date) {
        this.tableItem = tableItem;
        this.status = status;
        this.date = date;
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public List<OrderedDish> getOrderedDishes() {
        return orderedDishes;
    }

    public void setOrderedDishes(List<OrderedDish> orderedDishes) {
        this.orderedDishes = orderedDishes;
    }

}
