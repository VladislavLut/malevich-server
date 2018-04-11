package com.malevich.server.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tables")
public class TableItem implements Serializable {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "opened", nullable = false)
    private boolean opened;

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
}
