package com.malevich.server.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import static com.malevich.server.util.Strings.*;

@Entity
@Table(name = DISHES_TABLE_NAME)
public class Dish implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = DISHES_ID_COLUMN)
    private Integer id;

    @NotNull
    @Column(name = DISHES_CATEGORY_COLUMN)
    private String category;

    @NotNull
    @Column(name = DISHES_NAME_COLUMN)
    private String name;

    @NotNull
    @Column(name = DISHES_DESCRIPTION_COLUMN)
    private String description;

    @Column(name = DISHES_IMAGE_URL_COLUMN)
    private String imageURL;

    @NotNull
    @Column(name = DISHES_PRICE_COLUMN)
    private BigDecimal price;

    @Column(name = DISHES_RATING_COLUMN)
    private Float rating;

    @JsonIgnore
    @OneToMany(mappedBy = "dish", cascade = CascadeType.ALL)
    private List<OrderedDish> orderedDishes;

    protected Dish() {
    }

    public Dish(Integer id) {
        this.id = id;
    }

    public Dish(String category, String name, String description, String imageURL, BigDecimal price) {
        this(category, name, description, imageURL, price, null);
    }

    public Dish(String category, String name, String description, String imageURL, BigDecimal price, Float rating) {
        this.category = category;
        this.name = name;
        this.description = description;
        this.imageURL = imageURL;
        this.price = price;
        this.rating = rating;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public List<OrderedDish> getOrderedDishes() {
        return orderedDishes;
    }

    public void setOrderedDishes(List<OrderedDish> orderedDishes) {
        this.orderedDishes = orderedDishes;
    }
}
