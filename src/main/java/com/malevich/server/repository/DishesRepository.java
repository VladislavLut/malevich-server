package com.malevich.server.repository;

import com.malevich.server.entity.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface DishesRepository extends JpaRepository<Dish, Integer> {



    @Modifying(clearAutomatically = true)
    @Query("update Dish d " +
            "set d.price = :price, d.imageURL = :imageURL, d.rating = :rating, d.description = :description " +
            "where d.id = :id")
    int update(
            @Param("id")int id,
            @Param("price")BigDecimal price,
            @Param("imageURL")String imageURL,
            @Param("rating")float rating,
            @Param("description")String description
    );


}
