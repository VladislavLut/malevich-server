package com.malevich.server.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "tables")
public class TableItem implements Serializable {

    @Id
    @Column(name = "id")
    private int id;

    @NotNull
    @Column(name = "opened")
    private boolean opened;

    @OneToMany(mappedBy = "tableItem", cascade = CascadeType.ALL)
    private List<Order> orders;

    protected TableItem() {
    }

    public TableItem(int id, boolean opened) {
        this.id = id;
        this.opened = opened;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isOpened() {
        return opened;
    }

    public void setOpened(boolean opened) {
        this.opened = opened;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
