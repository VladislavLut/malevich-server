package com.malevich.server.entity;

import com.fasterxml.jackson.annotation.JsonView;
import com.malevich.server.view.Views;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

import static com.malevich.server.util.Strings.*;

@Entity
@Table(name = TABLES_TABLE_NAME)
public class TableItem implements Serializable {

    @JsonView(Views.Public.class)
    @Id
    @Column(name = TABLES_ID_COLUMN)
    private Integer id;

    @JsonView(Views.Public.class)
    @NotNull
    @Column(name = TABLES_OPENED_COLUMN)
    private Boolean opened;

    @JsonView(Views.Internal.class)
    @OneToMany(mappedBy = "tableItem", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Order> orders;

    @JsonView(Views.Internal.class)
    @OneToMany(mappedBy = "tableItem", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Reservation> reservations;

    public TableItem() {
    }

    public TableItem(Integer id) {
        this(id, false);
    }

    public TableItem(Integer id, Boolean opened) {
        this.id = id;
        this.opened = opened;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }
}
