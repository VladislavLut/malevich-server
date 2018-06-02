package com.malevich.server.controller;

import com.malevich.server.entity.Dish;
import com.malevich.server.exception.EntityAlreadyExistException;
import com.malevich.server.exception.EntityNotFoundException;
import com.malevich.server.repository.DishesRepository;
import com.malevich.server.repository.SessionsRepository;
import com.malevich.server.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

import static com.malevich.server.controller.UserController.SPACE_QUOTE;
import static com.malevich.server.util.UserType.ADMINISTRATOR;
import static com.malevich.server.util.ValidationUtil.validateAccess;
import static org.apache.logging.log4j.util.Chars.QUOTE;

@RestController
@RequestMapping("/menu")
public class DishController {

    @Autowired
    private final DishesRepository dishesRepository;

    @Autowired
    private final SessionsRepository sessionsRepository;

    @Autowired
    public DishController(DishesRepository dishesRepository, SessionsRepository sessionsRepository) {
        this.dishesRepository = dishesRepository;
        this.sessionsRepository = sessionsRepository;
    }

    @GetMapping("/all")
    public List<Dish> getMenu() {
        return this.dishesRepository.findAll();
    }

    @GetMapping("/category/{category}/")
    public List<Dish> getAllByCategory(@PathVariable String category) {
        return this.dishesRepository.findAllByCategory(category);
    }

    @GetMapping("/{id}/")
    public Optional<Dish> findDishById(@PathVariable int id) {
        validateDish(id);
        return this.dishesRepository.findById(id);
    }

    @PostMapping("/add")
    public String saveDish(@RequestBody final Dish dish, @CookieValue(name = "sid") String sid) {
        validateAccess(sessionsRepository, sid, ADMINISTRATOR);
        if (this.dishesRepository.findById(dish.getId()).isPresent()) {
            throw new EntityAlreadyExistException(
                    this.getClass().toString(), Dish.ID_COLUMN + SPACE_QUOTE + dish.getId() + QUOTE);
        }

        this.dishesRepository.save(dish);
        return Response.SAVED.name();
    }

    @PostMapping("/remove")
    public String removeDish(@RequestBody final Dish dish, @CookieValue(name = "sid") String sid) {
        validateAccess(sessionsRepository, sid, ADMINISTRATOR);
        validateDish(dish.getId());
        dishesRepository.deleteById(dish.getId());
        return Response.REMOVED.name();
    }

    @PostMapping("/update")
    public String update(@Valid @RequestBody final Dish dish, @CookieValue(name = "sid") String sid) {
        validateAccess(sessionsRepository, sid, ADMINISTRATOR);
        validateDish(dish.getId());
        this.dishesRepository.update(
                dish.getId(),
                dish.getPrice(),
                dish.getImageURL(),
                dish.getRating(),
                dish.getDescription()
        );
        return Response.UPDATED.name();
    }

    private void validateDish(int id) {
        this.dishesRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        this.getClass().toString(), Dish.ID_COLUMN + SPACE_QUOTE + id + QUOTE));
    }
}
