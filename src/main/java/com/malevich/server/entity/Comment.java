package com.malevich.server.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

import static com.malevich.server.util.Strings.*;

@Entity
@Table(name = COMMENTS_TABLE_NAME)
public class Comment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = COMMENTS_ID_COLUMN)
    private Integer id;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = COMMENTS_ORDER_ID_COLUMN, nullable = false)
    private Order order;

    @Column(name = COMMENTS_COMMENT_COLUMN)
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
