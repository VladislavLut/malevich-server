package com.malevich.server.repository;

import com.malevich.server.entity.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface DishesRepository extends JpaRepository<Dish, Integer> {

    List<Dish> findAllByCategory(String category);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = "UPDATE dishes " +
            "SET price = :price, imageURL = :imageURL, rating = :rating, description = :description " +
            "WHERE id = :id", nativeQuery = true)
    int update(
            @Param("id")int id,
            @Param("price")BigDecimal price,
            @Param("imageURL")String imageURL,
            @Param("rating")float rating,
            @Param("description")String description
    );


}
