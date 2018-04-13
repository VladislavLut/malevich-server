package com.malevich.server.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

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
    private Set<Order> orders;

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

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }
}
