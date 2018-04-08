package com.malevich.server.repository;

import com.malevich.server.entity.Dish;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DishesRepository extends JpaRepository<Dish, Integer> {

    Optional<Dish> findDishById(int id);

//    List<Dish> findAllByIdIsNotNull();
//
//    List<Dish> findAllByCategory(String category);
//
//    List<Dish> findAllByNameContaining(String name);
//
//    List<Dish> findAllByDescriptionContaining(String description);
//
//    List<Dish> findAllByRatingGreaterThan(float rating);
}
