package com.malevich.server.controller;

import com.malevich.server.entity.Order;
import com.malevich.server.entity.TableItem;
import com.malevich.server.http.response.status.exception.EntityAlreadyExistException;
import com.malevich.server.http.response.status.exception.EntityNotFoundException;
import com.malevich.server.http.response.status.exception.OkException;
import com.malevich.server.repository.OrdersRepository;
import com.malevich.server.utils.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

import static com.malevich.server.controller.UserController.QUOTE;
import static com.malevich.server.controller.UserController.SPACE_QUOTE;
import static com.malevich.server.http.response.status.exception.OkException.*;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private final OrdersRepository ordersRepository;

    @Autowired
    public OrderController(final OrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }

    @GetMapping("/all-orders")
    public List<Order> findAllOrders() {
        return this.ordersRepository.findAll();
    }

    @GetMapping("/{id}/")
    public Optional<Order> findOrderById(@PathVariable int id) {
        validateOrder(id);
        return this.ordersRepository.findById(id);
    }

    @GetMapping("/active-orders")
    public List<Order> findAllActiveOrders() {
        return this.ordersRepository.findAllByStatusNotLike(Status.CLOSED);
    }

    @GetMapping("/{status}/orders-by-status")
    public List<Order> findOrdersByStatusIgnoreCase(@PathVariable String status) {
        return this.ordersRepository.findAllByStatus(Status.valueOf(status));
    }

    @GetMapping("/{date}/orders-by-date")
    public List<Order> findOrdersByDate(@PathVariable String date) {
        return this.ordersRepository.findAllByDate(date);
    }

    @GetMapping("/{tableId}/orders-by-table-id")
    public List<Order> findOrdersByTableItemId(@PathVariable int tableId) {
        return this.ordersRepository.findAllByTableItemId(tableId);
    }

    @PostMapping("/active-order")
    public Optional<Order> findActiveOrderAtTable(@RequestBody final TableItem tableItem) {
        return this.ordersRepository.findFirstByTableItemAndStatusNotLike(tableItem, Status.CLOSED);
    }

    @PostMapping("/orders-by-table")
    public List<Order> findOrdersByTableItem(@RequestBody TableItem tableItem) {
        return this.ordersRepository.findAllByTableItem(tableItem);
    }

    @PostMapping("/add")
    public void saveOrder(@RequestBody final Order order) {
        if (this.ordersRepository.findById(order.getId()).isPresent()) {
            throw new EntityAlreadyExistException(
                    this.getClass().toString(), Order.ID_COLUMN + SPACE_QUOTE + order.getId() + QUOTE);
        }

        this.ordersRepository.save(order);

        throw new OkException(SAVED, this.getClass().toString());
    }

    @PostMapping("/remove")
    public void removeOrder(@RequestBody final Order order) {
        validateOrder(order.getId());

        ordersRepository.deleteById(order.getId());

        throw new OkException(REMOVED, this.getClass().toString());
    }

    @PostMapping("/update-status")
    public void updateOrderStatus(@Valid @RequestBody final Order order) {
        validateOrder(order.getId());
        this.ordersRepository.updateStatus(
                order.getId(),
                order.getStatus().name()
        );

        throw new OkException(
                UPDATED,
                this.getClass().toString(),
                Order.ID_COLUMN + SPACE_QUOTE + order.getId() + QUOTE);
    }

    private void validateOrder(int id) {
        this.ordersRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        this.getClass().toString(), Order.ID_COLUMN + SPACE_QUOTE + id + QUOTE));
    }
}