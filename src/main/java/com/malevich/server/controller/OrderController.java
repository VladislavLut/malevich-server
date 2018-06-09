package com.malevich.server.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.malevich.server.entity.Order;
import com.malevich.server.entity.OrderedDish;
import com.malevich.server.entity.TableItem;
import com.malevich.server.enums.Response;
import com.malevich.server.enums.Status;
import com.malevich.server.exception.EntityNotFoundException;
import com.malevich.server.exception.OrderIsClosedEception;
import com.malevich.server.repository.OrderedDishesRepository;
import com.malevich.server.repository.OrdersRepository;
import com.malevich.server.repository.SessionsRepository;
import com.malevich.server.service.AdminClientService;
import com.malevich.server.util.JsonUtil;
import com.malevich.server.view.Views;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.malevich.server.controller.SessionController.SID;
import static com.malevich.server.controller.UserController.SPACE_QUOTE;
import static com.malevich.server.enums.UserType.*;
import static com.malevich.server.util.ValidationUtil.validateAccess;
import static org.apache.logging.log4j.util.Chars.QUOTE;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private static final int SERVER_PORT = 3443;

    @Autowired
    private final OrdersRepository ordersRepository;

    @Autowired
    private final SessionsRepository sessionsRepository;

    @Autowired
    private final OrderedDishesRepository orderedDishesRepository;

    private AdminClientService adminClientService;

    @Autowired
    public OrderController(final OrdersRepository ordersRepository,
                           final SessionsRepository sessionsRepository,
                           final OrderedDishesRepository orderedDishesRepository) {
        this.ordersRepository = ordersRepository;
        this.sessionsRepository = sessionsRepository;
        this.orderedDishesRepository = orderedDishesRepository;
        adminClientService = new AdminClientService(SERVER_PORT);
    }

    @JsonView(Views.Internal.class)
    @GetMapping("/all")
    public List<Order> findAllOrders(@CookieValue(name = SID) String sid) {
        validateAccess(sessionsRepository, sid, KITCHENER, ADMINISTRATOR);
        return ordersRepository.findAll();
    }

    @GetMapping("/{id}/")
    public Optional<Order> findOrderById(@PathVariable int id, @CookieValue(name = SID) String sid) {
        validateAccess(sessionsRepository, sid, ADMINISTRATOR, KITCHENER);
        throwIfNotExist(id);
        return ordersRepository.findById(id);
    }

    @GetMapping("/active")
    public List<Order> findAllActiveOrders(@CookieValue(name = SID) String sid) {
        validateAccess(sessionsRepository, sid, ADMINISTRATOR, KITCHENER);
        return ordersRepository.findAllByStatusNotLike(Status.CLOSED);
    }

    @GetMapping("/status/{status}/")
    public List<Order> findOrdersByStatusIgnoreCase(@PathVariable String status, @CookieValue(name = SID) String sid) {
        validateAccess(sessionsRepository, sid, ADMINISTRATOR, KITCHENER);
        return ordersRepository.findAllByStatus(Status.valueOf(status));
    }

    @GetMapping("/date/{date}/")
    public List<Order> findOrdersByDate(@PathVariable String date, @CookieValue(name = SID) String sid) {
        validateAccess(sessionsRepository, sid, ADMINISTRATOR);
        return ordersRepository.findAllByDate(date);
    }

    @GetMapping("/table/{tableId}/")
    public List<Order> findOrdersByTableItemId(@PathVariable int tableId, @CookieValue(name = SID) String sid) {
        validateAccess(sessionsRepository, sid, ADMINISTRATOR);
        return ordersRepository.findAllByTableItemId(tableId);
    }

    @JsonView(Views.Internal.class)
    @PostMapping("/add")
    public Order saveOrder(@RequestBody final Order order, @CookieValue(name = SID) String sid) {
        validateAccess(sessionsRepository, sid, TABLE, CLIENT, ADMINISTRATOR);

        List<OrderedDish> dishes = order.getOrderedDishes();
        order.setOrderedDishes(new ArrayList<>());

        validateOrder(order);

        saveOrUpdateOrder(order);
        saveOrderedDishesList(dishes, order);

        order.setOrderedDishes(orderedDishesRepository.findAllByOrderId(order.getId()));
        return order;
    }

    @PostMapping("/remove")
    public String removeOrder(@RequestBody final Order order, @CookieValue(name = SID) String sid) {
        validateAccess(sessionsRepository, sid, ADMINISTRATOR, TABLE);
        throwIfNotExist(order.getId());

        ordersRepository.deleteById(order.getId());
        return Response.REMOVED.name();
    }

    @PostMapping("/update")
    public String updateOrderStatus(@Valid @RequestBody final Order order, @CookieValue(name = SID) String sid) {
        validateAccess(sessionsRepository, sid, ADMINISTRATOR, TABLE, KITCHENER);
        throwIfNotExist(order.getId());
        ordersRepository.updateStatus(
                order.getId(),
                order.getStatus().name()
        );
        adminClientService.send(JsonUtil.toJson(order));
        return Response.UPDATED.name();
    }

    private void validateOrder(@RequestBody Order order) {
        validateOrderTable(order);
        validateOrderStatus(order);
    }

    private void validateOrderTable(@RequestBody Order order) {
        if (order.getTableItem() == null) {
            order.setTableItem(new TableItem(-1));
        }
    }

    private void validateOrderStatus(@RequestBody Order order) {
        Status status = order.getStatus();
        order.setStatus(status == null ? Status.WAITING : status);
    }

    private void saveOrUpdateOrder(@RequestBody Order order) {
        Order old = ordersRepository.findById(order.getId()).orElse(null);
        if (old != null) {
            if (old.getStatus() == Status.CLOSED) {
                throw new OrderIsClosedEception(String.valueOf(order.getId()));
            }
            order.setStatus((old.getStatus() == Status.DONE) ? Status.WAITING : old.getStatus());
            ordersRepository.updateStatus(order.getId(), order.getStatus().name());
        } else {
            order.setId(ordersRepository.save(order).getId());
        }
    }

    private void saveOrderedDishesList(List<OrderedDish> dishes, Order order) {
        if (!dishes.isEmpty()) {
            for (OrderedDish dish : dishes) {
                dish.setOrder(order);
                dish.setStatus(Status.WAITING);
            }
            orderedDishesRepository.saveAll(dishes);
        }
    }

    private void throwIfNotExist(int id) {
        ordersRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        getClass().toString(), Order.ID_COLUMN + SPACE_QUOTE + id + QUOTE));
    }


}