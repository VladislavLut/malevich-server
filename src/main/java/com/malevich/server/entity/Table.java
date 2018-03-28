package com.malevich.server.entity;

import javax.persistence.Entity;
import java.io.Serializable;

@Entity
@javax.persistence.Table(name = "dishes")
public class Table implements Serializable {
    private int id;
    private boolean isOpened;

    public Table() {
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
