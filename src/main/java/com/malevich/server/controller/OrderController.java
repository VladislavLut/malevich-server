package com.malevich.server.controller;


import com.malevich.server.http.response.status.exception.EntityAlreadyExistException;
import com.malevich.server.http.response.status.exception.EntityNotFoundException;
import com.malevich.server.entity.Order;
import com.malevich.server.http.response.status.exception.OkException;
import com.malevich.server.repository.OrdersRepository;
import com.malevich.server.utils.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
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

    @GetMapping("/all")
    public List<Order> findAllOrders() {
        return this.ordersRepository.findAll();
    }

    @GetMapping("/active-orders")
    public List<Order> findAllActiveOrders() {
        return this.ordersRepository.findAllByStatusNotLikeIgnoreCase(Status.CLOSED.name());
    }

    @GetMapping("/{status}/orders-by-status")
    public List<Order> findOrdersByStatusIgnoreCase(@PathVariable String status) {
        return this.ordersRepository.findAllByStatusIgnoreCase(status);
    }

    @GetMapping("/{date}/orders-by-date")
    public List<Order> findOrdersByDate(@PathVariable String date) {
        return this.ordersRepository.findAllByDate(date);
    }

    @GetMapping("/{tableItem}/active-order")
    public Optional<Order> findActiveOrderAtTable(@PathVariable int tableId) {
        //TableController.validateTable(id);
        return this.ordersRepository.findFirstByTableAndStatusNotLikeIgnoreCase(tableId, Status.CLOSED.name());
    }

    @PostMapping("/add")
    public void saveOrder(@RequestBody final Order order) {
        if (this.ordersRepository.findById(order.getId()).isPresent()) {
            throw new EntityAlreadyExistException(this.getClass(), "id '" + order.getId() + "'");
        }

        this.ordersRepository.save(order);

        throw new OkException("order saved in the database");
    }

    @PostMapping("/remove")
    public void removeOrder(@RequestBody final Order order) {
        validateOrder(order.getId());

        ordersRepository.deleteById(order.getId());

        throw new OkException("order removed from the database");
    }

    @PostMapping("/update")
    @Transactional
    public void updateOrder(@Valid @RequestBody final Order order) {
        validateOrder(order.getId());
        this.ordersRepository.updateStatus(
                order.getId(),
                order.getStatus().name()
        );

        throw new OkException("order was updated");
    }

    private void validateOrder(int id) {
        this.ordersRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(this.getClass(), "id '" + id + "'."));
    }
}
