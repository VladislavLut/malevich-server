package com.malevich.server.controller;

import com.malevich.server.entity.Order;
import com.malevich.server.http.response.status.exception.EntityAlreadyExistException;
import com.malevich.server.http.response.status.exception.EntityNotFoundException;
import com.malevich.server.repository.OrdersRepository;
import com.malevich.server.util.Response;
import com.malevich.server.util.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

import static com.malevich.server.controller.UserController.QUOTE;
import static com.malevich.server.controller.UserController.SPACE_QUOTE;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private final OrdersRepository ordersRepository;

    @Autowired
    public OrderController(final OrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }

    @GetMapping("/all")
    public List<Order> findAllOrders() {
        return this.ordersRepository.findAll();
    }

    @GetMapping("/{id}/")
    public Optional<Order> findOrderById(@PathVariable int id) {
        validateOrder(id);
        return this.ordersRepository.findById(id);
    }

    @GetMapping("/active")
    public List<Order> findAllActiveOrders() {
        return this.ordersRepository.findAllByStatusNotLike(Status.CLOSED);
    }

    @GetMapping("/status/{status}/")
    public List<Order> findOrdersByStatusIgnoreCase(@PathVariable String status) {
        return this.ordersRepository.findAllByStatus(Status.valueOf(status));
    }

    @GetMapping("/date/{date}/")
    public List<Order> findOrdersByDate(@PathVariable String date) {
        return this.ordersRepository.findAllByDate(date);
    }

    @GetMapping("/table/{tableId}/")
    public List<Order> findOrdersByTableItemId(@PathVariable int tableId) {
        return this.ordersRepository.findAllByTableItemId(tableId);
    }

    @PostMapping("/add")
    public String saveOrder(@RequestBody final Order order) {
        if (this.ordersRepository.findById(order.getId()).isPresent()) {
            throw new EntityAlreadyExistException(
                    this.getClass().toString(), Order.ID_COLUMN + SPACE_QUOTE + order.getId() + QUOTE);
        }

        this.ordersRepository.save(order);
        return Response.SAVED.name();
    }

    @PostMapping("/remove")
    public String removeOrder(@RequestBody final Order order) {
        validateOrder(order.getId());

        ordersRepository.deleteById(order.getId());
        return Response.REMOVED.name();
    }

    @PostMapping("/update")
    public String updateOrderStatus(@Valid @RequestBody final Order order) {
        validateOrder(order.getId());
        this.ordersRepository.updateStatus(
                order.getId(),
                order.getStatus().name()
        );
        return Response.UPDATED.name();
    }

    private void validateOrder(int id) {
        this.ordersRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        this.getClass().toString(), Order.ID_COLUMN + SPACE_QUOTE + id + QUOTE));
    }
}