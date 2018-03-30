package com.malevich.server.repository;

import com.malevich.server.entity.Dish;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DishesRepository extends CrudRepository<Dish, Integer> {
    List<Dish> findAllByIdIsNotNull();

    Dish findDishBy(int id);

    List<Dish> findAllByCategory(String category);

    List<Dish> findAllByNameContaining(String name);

    List<Dish> findAllByDescriptionContaining(String description);

    List<Dish> findAllByRatingGreaterThan(float rating);
}
