package com.malevich.server.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@javax.persistence.Table(name = "orders")
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "table_id")
    private Table table;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "date", nullable = false)
    private String date;

    protected Order() {
    }

    public Order(Table table, String status, String date) {
        this.table = table;
        this.status = status;
        this.date = date;
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

}
