package com.malevich.server.controller;

import com.malevich.server.entity.Order;
import com.malevich.server.entity.User;
import com.malevich.server.repository.OrderedDishesRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.malevich.server.http.response.status.exception.EntityAlreadyExistException;
import com.malevich.server.http.response.status.exception.EntityNotFoundException;
import com.malevich.server.entity.OrderedDish;
import com.malevich.server.http.response.status.exception.OkException;

import com.malevich.server.utils.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

import static com.malevich.server.controller.UserController.QUOTE;
import static com.malevich.server.controller.UserController.SPACE_QUOTE;
import static com.malevich.server.http.response.status.exception.OkException.REMOVED;
import static com.malevich.server.http.response.status.exception.OkException.SAVED;
import static com.malevich.server.http.response.status.exception.OkException.UPDATED;

@RestController
@RequestMapping("/ordered-dishes")
public class OrderedDishController {
    @Autowired
    private final OrderedDishesRepository orderedDishesRepository;

    @Autowired
    public OrderedDishController(final OrderedDishesRepository  orderedDishesRepository) {
        this.orderedDishesRepository  = orderedDishesRepository;
    }

    @GetMapping("/{id}/")
    public Optional<OrderedDish> findOrderedDishById(@PathVariable int id) {
        return this.orderedDishesRepository.findById(id);
    }

    @GetMapping("/all-ordered-dishes")
    public List<OrderedDish> findAllOrderDishes() {
        return this.orderedDishesRepository.findAll();
    }

    @GetMapping("/{status}/find-by-status")
    public List<OrderedDish> findOrderDishesByStatus(@PathVariable String status) {
        return this.orderedDishesRepository.findAllByStatus(Status.valueOf(status));
    }

    @GetMapping("/{orderId}/find-by-order-id")
    public List<OrderedDish> findOrderDishesByOrderId(@PathVariable int orderId) {
        return this.orderedDishesRepository.findAllByOrderId(orderId);
    }

    @GetMapping("/{kitchenerId}/find-by-kitchener-id")
    public List<OrderedDish> findOrderDishesByKitchenerId(@PathVariable int kitchenerId) {
        return this.orderedDishesRepository.findAllByKitchenerId(kitchenerId);
    }

    @PostMapping("/find-by-order")
    public List<OrderedDish> findOrderedDishesByOrder(@RequestBody final Order order) {
        return this.orderedDishesRepository.findAllByOrder(order);
    }

    @PostMapping("/find-by-kitchener")
    public List<OrderedDish> findOrderedDishesByKitchener(@RequestBody final User kitchener) {
        return this.orderedDishesRepository.findAllByKitchener(kitchener);
    }

    @PostMapping("/add")
    public void saveOrderedDish(@RequestBody final OrderedDish orderedDish) {
        if (this.orderedDishesRepository.findById(orderedDish.getId()).isPresent()) {
            throw new EntityAlreadyExistException(
                    this.getClass().toString(), OrderedDish.ID_COLUMN + SPACE_QUOTE + orderedDish.getId() + QUOTE);
        }

        this.orderedDishesRepository.save(orderedDish);

        throw new OkException(SAVED, this.getClass().toString());
    }

    @PostMapping("/remove")
    public void removeOrderedDish(@RequestBody final OrderedDish orderedDish) {
        validateOrderedDish(orderedDish.getId());

        orderedDishesRepository.deleteById(orderedDish.getId());

        throw new OkException(REMOVED, this.getClass().toString());
    }

    @PostMapping("/update-status")
    public void updateOrderedDishStatus(@Valid @RequestBody final OrderedDish orderedDish) {
        validateOrderedDish(orderedDish.getId());
        this.orderedDishesRepository.updateStatus(
                orderedDish.getId(),
                orderedDish.getStatus().name()
        );

        throw new OkException(UPDATED, this.getClass().toString(), OrderedDish.ID_COLUMN + SPACE_QUOTE + orderedDish.getId() + QUOTE);
    }

    @PostMapping("/update-kitchener-and-status")
    public void updateOrderedDishKitchenerAndStatus(@Valid @RequestBody final OrderedDish orderedDish) {
        validateOrderedDish(orderedDish.getId());
        this.orderedDishesRepository.updateStatusAndKitchener(
                orderedDish.getId(),
                orderedDish.getStatus().name(),
                orderedDish.getKitchener().getId()
        );

        throw new OkException(UPDATED, this.getClass().toString(), OrderedDish.ID_COLUMN + SPACE_QUOTE + orderedDish.getId() + QUOTE);
    }

    private void validateOrderedDish(int id) {
        this.orderedDishesRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        this.getClass().toString(), OrderedDish.ID_COLUMN + SPACE_QUOTE + id + QUOTE));
    }
}
