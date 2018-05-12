package com.malevich.server.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.malevich.server.utils.Status;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Time;

@Entity
@Table(name = OrderedDish.TABLE_NAME)
public class OrderedDish implements Serializable {

    public static final String TABLE_NAME = "ordered_dishes";

    public static final String ID_COLUMN = "id";
    public static final String ORDER_ID_COLUMN = "order_id";
    public static final String DISH_ID_COLUMN = "dish_id";
    public static final String KITCHENER_ID_COLUMN = "kitchener_id";
    public static final String STATUS_COLUMN = "status";
    public static final String QUANTITY_COLUMN = "quantity";
    public static final String TIME_COLUMN = "time";
    public static final String COMMENT_COLUMN = "comment";

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = ID_COLUMN)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    @JoinColumn(name = ORDER_ID_COLUMN, nullable = false)
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    @JoinColumn(name = DISH_ID_COLUMN, nullable = false)
    private Dish dish;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    @JoinColumn(name = KITCHENER_ID_COLUMN, nullable = false)
    private User kitchener;

    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(name = STATUS_COLUMN)
    private Status status;

    @NotNull
    @Column(name = QUANTITY_COLUMN)
    private int quantity;

    @NotNull
    @Column(name = TIME_COLUMN)
    private Time time;

    @Column(name = COMMENT_COLUMN)
    private String comment;

    protected OrderedDish() {
    }

    public OrderedDish(int id) {
        this.id = -1;
        order = new Order(-1);
        dish = new Dish(-1);
        kitchener = new User(-1);
        status = Status.NULL;
        quantity = -1;
        time = new Time(0);
        comment = "";
    }

    public OrderedDish(Order order, Dish dish, User kitchener, Status status, int quantity, String time, String comment) {
        this.order = order;
        this.dish = dish;
        this.kitchener = kitchener;
        this.status = status;
        this.quantity = quantity;
        this.time = Time.valueOf(time);
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
