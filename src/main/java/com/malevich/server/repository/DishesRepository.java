package com.malevich.server.repository;

import com.malevich.server.entity.Dish;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface DishesRepository extends CrudRepository<Dish, Integer> {

    Optional<Dish> findDishBy(int id);

    List<Dish> findAllByIdIsNotNull();

    List<Dish> findAllByCategory(String category);

    List<Dish> findAllByNameContaining(String name);

    List<Dish> findAllByDescriptionContaining(String description);

    List<Dish> findAllByRatingGreaterThan(float rating);
}
