package com.malevich.server.entity;

import javax.persistence.*;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "ordered_dishes")
public class OrderedDish implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(name = "dish_id", nullable = false)
    private Dish dish;

    @ManyToOne
    @JoinColumn(name = "kitchener_id", nullable = false)
    private User kitchener;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "time", nullable = false)
    private String time;

    @Column(name = "comment")
    private String comment;

    protected OrderedDish() {
    }

    public OrderedDish(Order order, Dish dish, User kitchener, String status, int quantity, String time, String comment) {
        this.order = order;
        this.dish = dish;
        this.kitchener = kitchener;
        this.status = status;
        this.quantity = quantity;
        this.time = time;
        this.comment = comment;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    public User getKitchener() {
        return kitchener;
    }

    public void setKitchener(User kitchener) {
        this.kitchener = kitchener;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
