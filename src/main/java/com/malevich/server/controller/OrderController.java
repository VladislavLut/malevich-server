package com.malevich.server.controller;


import com.malevich.server.controller.exception.EntityAlreadyExistException;
import com.malevich.server.controller.exception.EntityNotFoundException;
import com.malevich.server.entity.Order;
import com.malevich.server.repository.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private final OrdersRepository ordersRepository;

    @Autowired
    public OrderController(final OrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }

    @GetMapping("/{id}/")
    public Optional<Order> findOrderById(@PathVariable int id) {
        validateOrder(id);
        return this.ordersRepository.findById(id);
    }

    @PostMapping("/add")
    public ResponseEntity<?> saveOrder(@RequestBody final Order order) {
        if (this.ordersRepository.findById(order.getId()).isPresent()) {
            throw new EntityAlreadyExistException(this.getClass(), "id '" + order.getId() + "'");
        }

        this.ordersRepository.save(order);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/remove")
    public ResponseEntity<?> removeOrder(@RequestBody final Order order) {
        validateOrder(order.getId());

        ordersRepository.deleteById(order.getId());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/update")
    @Transactional
    public ResponseEntity<?> updateOrder(@Valid @RequestBody final Order order) {
        validateOrder(order.getId());
        this.ordersRepository.updateStatus(
                order.getId(),
                order.getStatus().name()
        );

        return new ResponseEntity<>(HttpStatus.OK);
    }

    private void validateOrder(int id) {
        this.ordersRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(this.getClass(), "id '" + id + "'."));
    }
}
