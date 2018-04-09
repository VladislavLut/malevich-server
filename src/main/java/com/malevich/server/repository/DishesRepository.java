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

//    List<Dish> findAllByCategory(String category);
//
//    List<Dish> findAllByNameContaining(String name);
//
//    List<Dish> findAllByDescriptionContaining(String description);
//
//    List<Dish> findAllByRatingGreaterThan(float rating);

    @Modifying(clearAutomatically = true)
    @Query("update dishes d " +
            "set d.price = :price, d.imageURL = :imageURL, d.rating = :rating, d.description = :descriprion " +
            "where d.id = :id")
    int updatePrice(@Param("id") int id,
                    @Param("price")BigDecimal price,
                    @Param("imageURL") String imageURL,
                    @Param("rating")float rating,
                    @Param("description")String description
    );


}
