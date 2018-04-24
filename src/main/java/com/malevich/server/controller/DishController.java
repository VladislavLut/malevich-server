package com.malevich.server.controller;

import com.malevich.server.entity.Dish;
import com.malevich.server.http.response.status.exception.EntityAlreadyExistException;
import com.malevich.server.http.response.status.exception.EntityNotFoundException;
import com.malevich.server.http.response.status.exception.OkException;
import com.malevich.server.repository.DishesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/menu")
public class DishController {

    @Autowired
    private final DishesRepository dishesRepository;

    @Autowired
    public DishController(DishesRepository dishesRepository) {
        this.dishesRepository = dishesRepository;
    }

    @GetMapping("/")
    public List<Dish> getMenu() {
        return this.dishesRepository.findAll();
    }

    @GetMapping("/{id}/")
    public Optional<Dish> findDishById(@PathVariable int id) {
        validateDish(id);
        return this.dishesRepository.findById(id);
    }

    @PostMapping("/add")
    public void saveDish(@RequestBody final Dish dish) {
        if (this.dishesRepository.findById(dish.getId()).isPresent()) {
            throw new EntityAlreadyExistException(this.getClass(), "id '" + dish.getId() + "'");
        }

        this.dishesRepository.save(dish);

        throw new OkException("dish saved in the database");
    }

    @PostMapping("/remove")
    public void removeDish(@RequestBody final Dish dish) {
        validateDish(dish.getId());

        dishesRepository.deleteById(dish.getId());

        throw new OkException("dish from the database");
    }

    @PostMapping("/update")
    @Transactional
    public void update(@Valid @RequestBody final Dish dish) {
        validateDish(dish.getId());
        this.dishesRepository.update(
                dish.getId(),
                dish.getPrice(),
                dish.getImageURL(),
                dish.getRating(),
                dish.getDescription()
        );

        throw new OkException("dish was updated");
    }

    private void validateDish(int id) {
        this.dishesRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(this.getClass(), "id '" + id + "'."));
    }
}
