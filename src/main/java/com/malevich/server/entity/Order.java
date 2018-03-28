package com.malevich.server.entity;

import javax.persistence.Entity;
import java.io.Serializable;

@Entity
@javax.persistence.Table(name = "orders")
public class Order implements Serializable {
    private int id;
    private Table table;
    private String status;
    private String type;
    private String date;

    public Order() {
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
