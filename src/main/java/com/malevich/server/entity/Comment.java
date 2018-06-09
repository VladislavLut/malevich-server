package com.malevich.server.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.malevich.server.view.Views;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = Comment.TABLE_NAME)
public class Comment implements Serializable {

    public static final String TABLE_NAME = "comments";

    public static final String ID_COLUMN = "id";
    public static final String ORDER_ID_COLUMN = "order_id";
    public static final String COMMENT_COLUMN = "comment";

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = ID_COLUMN)
    private Integer id;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = ORDER_ID_COLUMN, nullable = false)
    private Order order;

    @Column(name = COMMENT_COLUMN)
    private String comment;

    protected Comment() {
    }

    public Comment(Integer orderId) {
        order = new Order(orderId);
    }

    public Comment(Integer orderId, String comment) {
        this(new Order(orderId), comment);
    }

    public Comment(Order order, String comment) {
        this.order = order;
        this.comment = comment;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
