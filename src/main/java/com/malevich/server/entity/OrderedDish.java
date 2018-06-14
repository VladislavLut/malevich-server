package com.malevich.server.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.malevich.server.enums.Status;
import com.malevich.server.view.Views;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Time;

import static com.malevich.server.util.Strings.*;

@Entity
@Table(name = ORDERED_DISHES_TABLE_NAME)
public class OrderedDish implements Serializable {

    @JsonView(Views.Internal.class)
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = ORDERED_DISHES_ID_COLUMN)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    @JoinColumn(name = ORDERED_DISHES_ORDER_ID_COLUMN, nullable = false)
    private Order order;

    @JsonView(Views.Internal.class)
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    @JoinColumn(name = ORDERED_DISHES_DISH_ID_COLUMN, nullable = false)
    private Dish dish;

    @JsonView(Views.Internal.class)
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    @JoinColumn(name = ORDERED_DISHES_KITCHENER_ID_COLUMN, nullable = true)
    private User kitchener;

    @JsonView(Views.Internal.class)
    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(name = ORDERED_DISHES_STATUS_COLUMN)
    private Status status;

    @JsonView(Views.Internal.class)
    @NotNull
    @Column(name = ORDERED_DISHES_QUANTITY_COLUMN)
    private Integer quantity;

    @JsonView(Views.Internal.class)
    @NotNull
    @Column(name = ORDERED_DISHES_TIME_COLUMN)
    private Time time;

    @JsonView(Views.Internal.class)
    @Column(name = ORDERED_DISHES_COMMENT_COLUMN)
    private String comment;

    protected OrderedDish() {
    }

    public OrderedDish(Integer id) {
        this.id = id;
    }

    public OrderedDish(Integer orderId, Integer dishId, Integer kitchenerId, Integer quantity, String time, String comment) {
        this(new Order(orderId), new Dish(dishId), new User(kitchenerId), quantity, time, comment);
    }

    public OrderedDish(Order order, Dish dish, Integer quantity, String time, String comment) {
        this(order, dish, null, quantity, time, comment);
    }

    public OrderedDish(Order order, Dish dish, User kitchener, Integer quantity, String time, String comment) {
        this(order, dish, kitchener, Status.WAITING, quantity, time, comment);
    }

    public OrderedDish(Order order, Dish dish, User kitchener, Status status, Integer quantity, String time, String comment) {
        this.order = order;
        this.dish = dish;
        this.kitchener = kitchener;
        this.status = (status == null) ? Status.WAITING : status;
        this.quantity = quantity;
        this.time = Time.valueOf(time);
        this.comment = comment;
    }

    public Integer getId() {

        return id;
    }

    public void setId(Integer id) {
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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
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

    @Override
    public boolean equals(Object obj) {
        return dish.getId().equals(((OrderedDish) obj).getDish().getId());
    }
}
