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
@RestController
@RequestMapping("/ordered-dish")
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

    @GetMapping("/all")
    public List<OrderedDish> findAllOrderDishes() {
        return this.orderedDishesRepository.findAll();
    }

    @GetMapping("/find/{status}/")
    public List<OrderedDish> findOrderDishesByStatus(@PathVariable String status) {
        return this.orderedDishesRepository.findAllByStatus(Status.valueOf(status));
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
            throw new EntityAlreadyExistException(this.getClass(), "id '" + orderedDish.getId() + "'");
        }

        this.orderedDishesRepository.save(orderedDish);

        throw new OkException("ordered dish saved in the database");
    }

    @PostMapping("/remove")
    public void removeOrderedDish(@RequestBody final OrderedDish orderedDish) {
        validateOrder(orderedDish.getId());

        orderedDishesRepository.deleteById(orderedDish.getId());

        throw new OkException("ordered dish removed from the database");
    }

    @PostMapping("/update")
    @Transactional
    public void updateOrderedDish(@Valid @RequestBody final OrderedDish orderedDish) {
        validateOrder(orderedDish.getId());
        this.orderedDishesRepository.updateStatus(
                orderedDish.getId(),
                orderedDish.getStatus().name()
        );

        throw new OkException("ordered dish was updated");
    }

    private void validateOrder(int id) {
        this.orderedDishesRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(this.getClass(), "id '" + id + "'."));
    }
}
