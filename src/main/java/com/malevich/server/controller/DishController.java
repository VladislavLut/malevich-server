package com.malevich.server.controller;

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

import static com.malevich.server.controller.SessionController.SID;
import static com.malevich.server.controller.UserController.SPACE_QUOTE;
import static com.malevich.server.enums.UserType.ADMINISTRATOR;
import static com.malevich.server.util.ValidationUtil.validateAccess;
import static org.apache.logging.log4j.util.Chars.QUOTE;

@RestController
@RequestMapping("/menu")
public class DishController {

    @Autowired
    private final DishesRepository dishesRepository;

    @Autowired
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

    @PostMapping("/add")
    public String saveDish(@RequestBody final Dish dish, @CookieValue(name = SID) String sid) {
        validateAccess(sessionsRepository, sid, ADMINISTRATOR);
        if (dishesRepository.findById(dish.getId()).isPresent()) {
            throw new EntityAlreadyExistException(
                    getClass().toString(), Dish.ID_COLUMN + SPACE_QUOTE + dish.getId() + QUOTE);
        }

        dishesRepository.save(dish);
        timestamp.setTime(System.currentTimeMillis());
        return Response.SAVED.name();
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
    public String update(@Valid @RequestBody final Dish dish, @CookieValue(name = SID) String sid) {
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
        return Response.UPDATED.name();
    }

    private void validateDish(int id) {
        dishesRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        getClass().getSimpleName(), Dish.ID_COLUMN + SPACE_QUOTE + id + QUOTE));
    }
}
