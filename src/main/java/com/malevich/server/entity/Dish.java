package com.malevich.server.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = Dish.TABLE_NAME)
public class Dish implements Serializable{

    public static final String TABLE_NAME = "dishes";

    public static final String ID_COLUMN = "id";
    public static final String CATEGORY_COLUMN = "category";
    public static final String NAME_COLUMN = "name";
    public static final String DESCRIPTION_COLUMN = "description";
    public static final String IMAGE_URL_COLUMN = "imageurl";
    public static final String PRICE_COLUMN = "price";
    public static final String RATING_COLUMN = "rating";


    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = ID_COLUMN)
    private int id;

    @NotNull
    @Column(name = CATEGORY_COLUMN)
    private String category;

    @NotNull
    @Column(name = NAME_COLUMN)
    private String name;

    @NotNull
    @Column(name = DESCRIPTION_COLUMN)
    private String description;

    @Column(name = IMAGE_URL_COLUMN)
    private String imageURL;

    @NotNull
    @Column(name = PRICE_COLUMN)
    private BigDecimal price;

    @Column(name = RATING_COLUMN)
    private float rating;

    @JsonIgnore
    @OneToMany(mappedBy = "dish", cascade = CascadeType.ALL)
    private List<OrderedDish> orderedDishes;

    protected Dish() {
    }

    public Dish(int id) {
        this.id = -1;
        category = "";
        name = "";
        description = "";
        imageURL = "";
        price = new BigDecimal(0);
        rating = 0f;
    }

    public Dish(String category, String name, String description, String imageURL, BigDecimal price, float rating) {
        this.category = category;
        this.name = name;
        this.description = description;
        this.imageURL = imageURL;
        this.price = price;
        this.rating = rating;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {
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
