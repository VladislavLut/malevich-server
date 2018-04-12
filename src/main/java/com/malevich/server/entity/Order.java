package com.malevich.server.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "orders")
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "table_id")
    private TableItem tableItem;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "date", nullable = false)
    private String date;

    @OneToOne(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "order")
    private Comment comment;

    protected Order() {
    }

    public Order(TableItem tableItem, String status, String date) {
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
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
}
