package com.malevich.server.entity;

import com.malevich.server.utils.Status;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

import static com.malevich.server.entity.OrderedDish.TABLE_NAME;

@Entity
@Table(name = TABLE_NAME)
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

    @ManyToOne
    @JoinColumn(name = ORDER_ID_COLUMN, nullable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(name = DISH_ID_COLUMN, nullable = false)
    private Dish dish;

    @ManyToOne
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
    private String time;

    @Column(name = COMMENT_COLUMN)
    private String comment;

    protected OrderedDish() {
    }

    public OrderedDish(Order order, Dish dish, User kitchener, Status status, int quantity, String time, String comment) {
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
