package com.malevich.server.controller;

import com.malevich.server.entity.OrderedDish;
import com.malevich.server.exception.EntityAlreadyExistException;
import com.malevich.server.exception.EntityNotFoundException;
import com.malevich.server.repository.OrderedDishesRepository;
import com.malevich.server.repository.SessionsRepository;
import com.malevich.server.util.Response;
import com.malevich.server.util.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

import static com.malevich.server.controller.UserController.SPACE_QUOTE;
import static com.malevich.server.util.UserType.*;
import static com.malevich.server.util.ValidationUtil.validateAccess;
import static org.apache.logging.log4j.util.Chars.QUOTE;

@CrossOrigin(origins = "https://malevich-website.herokuapp.com")
@RestController
@RequestMapping("/dishes")
public class OrderedDishController {
    @Autowired
    private final OrderedDishesRepository orderedDishesRepository;

    @Autowired
    private final SessionsRepository sessionsRepository;

    @Autowired
    public OrderedDishController(final OrderedDishesRepository orderedDishesRepository, final SessionsRepository sessionsRepository) {
        this.orderedDishesRepository = orderedDishesRepository;
        this.sessionsRepository = sessionsRepository;
    }

    @GetMapping("/{id}/")
    public Optional<OrderedDish> findOrderedDishById(@PathVariable int id, @CookieValue(name = "sid") String sid) {
        validateAccess(sessionsRepository, sid, ADMINISTRATOR, KITCHENER, TABLE);
        validateOrderedDish(id);
        return this.orderedDishesRepository.findById(id);
    }

    @GetMapping("/all")
    public List<OrderedDish> findAllOrderedDishes(@CookieValue(name = "sid") String sid) {
        validateAccess(sessionsRepository, sid, ADMINISTRATOR, KITCHENER);
        return this.orderedDishesRepository.findAll();
    }

    @GetMapping("/status/{status}/")
    public List<OrderedDish> findOrderDishesByStatus(@PathVariable String status, @CookieValue(name = "sid") String sid) {
        validateAccess(sessionsRepository, sid, ADMINISTRATOR, KITCHENER);
        return this.orderedDishesRepository.findAllByStatus(Status.valueOf(status));
    }

    @GetMapping("/order/{orderId}/")
    public List<OrderedDish> findOrderDishesByOrderId(@PathVariable int orderId, @CookieValue(name = "sid") String sid) {
        validateAccess(sessionsRepository, sid, ADMINISTRATOR, KITCHENER, TABLE);
        return this.orderedDishesRepository.findAllByOrderId(orderId);
    }

    @GetMapping("/kitchener/{kitchenerId}/")
    public List<OrderedDish> findOrderDishesByKitchenerId(@PathVariable int kitchenerId, @CookieValue(name = "sid") String sid) {
        validateAccess(sessionsRepository, sid, ADMINISTRATOR, KITCHENER);
        return this.orderedDishesRepository.findAllByKitchenerId(kitchenerId);
    }

    @PostMapping("/add")
    public String saveOrderedDish(@RequestBody final OrderedDish orderedDish, @CookieValue(name = "sid") String sid) {
        validateAccess(sessionsRepository, sid, TABLE, ADMINISTRATOR);
        if (this.orderedDishesRepository.findById(orderedDish.getId()).isPresent()) {
            throw new EntityAlreadyExistException(
                    this.getClass().toString(), OrderedDish.ID_COLUMN + SPACE_QUOTE + orderedDish.getId() + QUOTE);
        }

        this.orderedDishesRepository.save(orderedDish);
        return Response.SAVED.name();
    }

    @PostMapping("/remove")
    public String removeOrderedDish(@RequestBody final OrderedDish orderedDish, @CookieValue(name = "sid") String sid) {
        validateAccess(sessionsRepository, sid, ADMINISTRATOR, TABLE);
        validateOrderedDish(orderedDish.getId());

        orderedDishesRepository.deleteById(orderedDish.getId());
        return Response.REMOVED.name();
    }

    @PostMapping("/update")
    public String updateOrderedDishKitchenerAndStatus(@Valid @RequestBody final OrderedDish orderedDish, @CookieValue(name = "sid") String sid) {
        validateAccess(sessionsRepository, sid, ADMINISTRATOR, KITCHENER, TABLE);
        validateOrderedDish(orderedDish.getId());
        this.orderedDishesRepository.updateStatusAndKitchener(
                orderedDish.getId(),
                orderedDish.getStatus().name(),
                orderedDish.getKitchener().getId()
        );
        return Response.UPDATED.name();
    }

    private void validateOrderedDish(int id) {
        this.orderedDishesRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        this.getClass().toString(), OrderedDish.ID_COLUMN + SPACE_QUOTE + id + QUOTE));
    }
}
