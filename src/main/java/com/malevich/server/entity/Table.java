package com.malevich.server.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@javax.persistence.Table(name = "dishes")
public class Table implements Serializable {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "is_opened", nullable = false)
    private boolean isOpened;

    protected Table() {
    }

    public Table(int id, boolean isOpened) {
        this.id = id;
        this.isOpened = isOpened;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isOpened() {
        return isOpened;
    }

    public void setOpened(boolean opened) {
        isOpened = opened;
    }
}
