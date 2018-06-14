package com.malevich.server.controller.rest;

import com.malevich.server.entity.Dish;
import com.malevich.server.enums.Response;
import com.malevich.server.exception.EntityAlreadyExistException;
import com.malevich.server.exception.EntityNotFoundException;
import com.malevich.server.repository.DishesRepository;
import com.malevich.server.repository.SessionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static com.malevich.server.controller.rest.SessionController.SID;
import static com.malevich.server.controller.rest.UserController.SPACE_QUOTE;
import static com.malevich.server.enums.UserType.ADMINISTRATOR;
import static com.malevich.server.util.Strings.DISHES_ID_COLUMN;
import static com.malevich.server.util.ValidationUtil.validateAccess;
import static org.apache.logging.log4j.util.Chars.QUOTE;

@RestController
@RequestMapping("/menu")
public class DishController {

    private final DishesRepository dishesRepository;

    private final SessionsRepository sessionsRepository;

    private Timestamp timestamp;

    @Autowired
    public DishController(DishesRepository dishesRepository, SessionsRepository sessionsRepository) {
        this.dishesRepository = dishesRepository;
        this.sessionsRepository = sessionsRepository;
        timestamp = new Timestamp(System.currentTimeMillis());
    }

    @GetMapping("/all")
    public List<Dish> getMenu() {
//        validateAccess(sessionsRepository, sid, true);
        return dishesRepository.findAll();
    }

    @GetMapping("/category/{category}/")
    public List<Dish> getAllByCategory(@PathVariable String category, @CookieValue(name = SID) String sid) {
        validateAccess(sessionsRepository, sid, true);
        return dishesRepository.findAllByCategory(category);
    }

    @GetMapping("/{id}/")
    public Optional<Dish> findDishById(@PathVariable int id, @CookieValue(name = SID) String sid) {
        validateAccess(sessionsRepository, sid, true);
        validateDish(id);
        return dishesRepository.findById(id);
    }

    @GetMapping("/stamp")
    public Timestamp getTimeStamp() {
        return timestamp;
    }

    @PostMapping("/all/in")
    public List<Dish> findAllIn(@RequestBody Set<Integer> ids, @CookieValue(SID) String sid) {
        validateAccess(sessionsRepository, sid, true);
        validateDishes(ids);
        return dishesRepository.findAllByIdIn(ids).get();
    }

    @PostMapping("/add")
    public Dish saveDish(@RequestBody final Dish dish, @CookieValue(name = SID) String sid) {
        validateAccess(sessionsRepository, sid, ADMINISTRATOR);
        if (dishesRepository.findById(dish.getId()).isPresent()) {
            throw new EntityAlreadyExistException(
                    getClass().toString(), DISHES_ID_COLUMN + SPACE_QUOTE + dish.getId() + QUOTE);
        }

        dishesRepository.save(dish);
        timestamp.setTime(System.currentTimeMillis());
        return dish;
    }

    @PostMapping("/remove")
    public String removeDish(@RequestBody final Dish dish, @CookieValue(name = SID) String sid) {
        validateAccess(sessionsRepository, sid, ADMINISTRATOR);
        validateDish(dish.getId());
        dishesRepository.deleteById(dish.getId());
        timestamp.setTime(System.currentTimeMillis());
        return Response.REMOVED.name();
    }

    @PostMapping("/update")
    public Dish update(@Valid @RequestBody final Dish dish, @CookieValue(name = SID) String sid) {
        validateAccess(sessionsRepository, sid, ADMINISTRATOR);
        validateDish(dish.getId());
        dishesRepository.update(
                dish.getId(),
                dish.getCategory(),
                dish.getPrice(),
                dish.getImageURL(),
                dish.getRating(),
                dish.getDescription()
        );
        timestamp.setTime(System.currentTimeMillis());
        return dish;
    }

    private void validateDishes(Set<Integer> ids) {
        validateDishes(dishesRepository, ids);
    }

    private void validateDish(int id) {
        validateDish(dishesRepository, id);
    }

    public static void validateDish(DishesRepository dishesRepository, Integer id) {
        dishesRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        Dish.class.getSimpleName(), DISHES_ID_COLUMN + SPACE_QUOTE + id + QUOTE));
    }

    public static void validateDishes(DishesRepository dishesRepository, Set<Integer> ids) {
        dishesRepository.findAllByIdIn(ids)
                .orElseThrow(() -> new EntityNotFoundException(
                        Dish.class.getSimpleName(), DISHES_ID_COLUMN + SPACE_QUOTE + ids.toString() + QUOTE));
    }
}
