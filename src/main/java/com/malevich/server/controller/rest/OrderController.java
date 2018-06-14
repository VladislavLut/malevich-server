package com.malevich.server.controller.rest;

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
import com.malevich.server.view.Views;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.malevich.server.controller.rest.SessionController.SID;
import static com.malevich.server.controller.rest.UserController.SPACE_QUOTE;
import static com.malevich.server.enums.UserType.*;
import static com.malevich.server.util.Strings.ORDERS_ID_COLUMN;
import static com.malevich.server.util.ValidationUtil.validateAccess;
import static org.apache.logging.log4j.util.Chars.QUOTE;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private static final int SERVER_PORT = 3443;

    private final OrdersRepository ordersRepository;

    private final SessionsRepository sessionsRepository;

    private final OrderedDishesRepository orderedDishesRepository;

    private final SimpMessageSendingOperations messagingTemplate;

    @Autowired
    public OrderController(final OrdersRepository ordersRepository,
                           final SessionsRepository sessionsRepository,
                           final OrderedDishesRepository orderedDishesRepository, SimpMessageSendingOperations messagingTemplate) {
        this.ordersRepository = ordersRepository;
        this.sessionsRepository = sessionsRepository;
        this.orderedDishesRepository = orderedDishesRepository;
        this.messagingTemplate = messagingTemplate;
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
        return ordersRepository.findAllByStatusAndCurrentDate(Status.valueOf(status));
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
        messagingTemplate.convertAndSend("/topic/public", order);
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
    public Order updateOrderStatus(@Valid @RequestBody final Order order, @CookieValue(name = SID) String sid) {
        validateAccess(sessionsRepository, sid, ADMINISTRATOR, TABLE, KITCHENER);
        throwIfNotExist(order.getId());
        ordersRepository.updateStatus(
                order.getId(),
                order.getStatus().name()
        );
        messagingTemplate.convertAndSend("/topic/public", order);
        return order;
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
                        getClass().toString(), ORDERS_ID_COLUMN + SPACE_QUOTE + id + QUOTE));
    }


}