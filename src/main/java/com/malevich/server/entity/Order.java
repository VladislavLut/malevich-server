package com.malevich.server.entity;

import com.fasterxml.jackson.annotation.*;
import com.malevich.server.enums.Status;
import com.malevich.server.view.Views;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import static com.malevich.server.util.Strings.*;

@Entity
@Table(name = ORDERS_TABLE_NAME)
public class Order implements Serializable {

    @JsonView(Views.Public.class)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orders_generator")
    @SequenceGenerator(name = "orders_generator", sequenceName = "orders_seq")
    @Column(name = ORDERS_ID_COLUMN)
    private Integer id;

    @JsonView(Views.Public.class)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = ORDERS_ID_COLUMN)
    @JsonIdentityReference(alwaysAsId = true)
    @ManyToOne
    @JoinColumn(name = ORDERS_TABLE_ID_COLUMN)
    private TableItem tableItem;

    @JsonView(Views.Public.class)
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = ORDERS_STATUS_COLUMN)
    private Status status;

    @JsonView(Views.Public.class)
    @NotNull
    @Column(name = ORDERS_DATE_COLUMN)
    private Date date;

    @JsonView(Views.Public.class)
    @Column(name = ORDERS_COMMENT_COLUMN)
    private String comment;

    @JsonView(Views.Public.class)
    @JsonIgnore
    @OneToOne(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Comment commentEntity;

    @JsonView(Views.Internal.class)
    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<OrderedDish> orderedDishes;

    protected Order() {
    }

    public Order(Integer id) {
        this.id = id;
    }

    public Order(Integer tableId, String date) {
        this(new TableItem(tableId), date);
    }

    public Order(TableItem tableItem, String date) {
        this(tableItem, date, null);
    }

    public Order(Integer tableId, String date, List<Integer> dishesId) {
        this(tableId, date);
        for (Integer dishId : dishesId) {
            orderedDishes.add(new OrderedDish(dishId));
        }
    }

    public Order(TableItem tableItem, String date, List<OrderedDish> dishes) {
        this(tableItem, Status.WAITING, date, dishes);
    }

    public Order(TableItem tableItem, Status status, String date, List<OrderedDish> dishes) {
        this(tableItem, status, date, null, dishes);
    }

    public Order(TableItem tableItem, Status status, String date, String comment, List<OrderedDish> dishes) {
        this.tableItem = tableItem;
        this.status = (status == null) ? Status.WAITING : status;
        this.date = Date.valueOf(date);
        this.orderedDishes = dishes;
        this.comment = comment;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TableItem getTableItem() {
        return tableItem;
    }

    public void setTableItem(TableItem tableItem) {
        this.tableItem = tableItem;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Comment getCommentEntity() {
        return commentEntity;
    }

    public void setCommentEntity(Comment commentEntity) {
        this.commentEntity = commentEntity;
    }

    public List<OrderedDish> getOrderedDishes() {
        return orderedDishes;
    }

    public void setOrderedDishes(List<OrderedDish> orderedDishes) {
        this.orderedDishes = orderedDishes;
    }

}
