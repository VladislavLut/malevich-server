package com.malevich.server.controller.rest;

import com.malevich.server.entity.OrderedDish;
import com.malevich.server.entity.User;
import com.malevich.server.enums.Response;
import com.malevich.server.enums.Status;
import com.malevich.server.exception.EntityNotFoundException;
import com.malevich.server.repository.OrderedDishesRepository;
import com.malevich.server.repository.OrdersRepository;
import com.malevich.server.repository.SessionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

import static com.malevich.server.controller.rest.SessionController.SID;
import static com.malevich.server.controller.rest.UserController.SPACE_QUOTE;
import static com.malevich.server.enums.Status.*;
import static com.malevich.server.enums.UserType.*;
import static com.malevich.server.util.Strings.ORDERED_DISHES_ID_COLUMN;
import static com.malevich.server.util.ValidationUtil.validateAccess;
import static org.apache.logging.log4j.util.Chars.QUOTE;

@RestController
@RequestMapping("/dishes")
public class OrderedDishController {

    private final OrderedDishesRepository orderedDishesRepository;

    private final OrdersRepository ordersRepository;

    private final SessionsRepository sessionsRepository;

    private final SimpMessageSendingOperations messagingTemplate;

    @Autowired
    public OrderedDishController(final OrderedDishesRepository orderedDishesRepository,
                                 final SessionsRepository sessionsRepository,
                                 final OrdersRepository ordersRepository, SimpMessageSendingOperations messagingTemplate) {
        this.orderedDishesRepository = orderedDishesRepository;
        this.sessionsRepository = sessionsRepository;
        this.ordersRepository = ordersRepository;
        this.messagingTemplate = messagingTemplate;
    }

    @GetMapping("/{id}/")
    public Optional<OrderedDish> findOrderedDishById(@PathVariable int id, @CookieValue(name = SID) String sid) {
        validateAccess(sessionsRepository, sid, ADMINISTRATOR, KITCHENER, TABLE);
        validateOrderedDish(id);
        return orderedDishesRepository.findById(id);
    }

    @GetMapping("/all")
    public List<OrderedDish> findAllOrderedDishes(@CookieValue(name = SID) String sid) {
        validateAccess(sessionsRepository, sid, ADMINISTRATOR, KITCHENER);
        return orderedDishesRepository.findAll();
    }

    @GetMapping("/status/{status}/")
    public List<OrderedDish> findOrderDishesByStatus(@PathVariable String status, @CookieValue(name = SID) String sid) {
        validateAccess(sessionsRepository, sid, ADMINISTRATOR, KITCHENER);
        return orderedDishesRepository.findAllByCurrentDateAndStatusOrderByTimeDesc(Status.valueOf(status));
    }

    @GetMapping("/order/{orderId}/")
    public List<OrderedDish> findOrderDishesByOrderId(@PathVariable int orderId, @CookieValue(name = SID) String sid) {
        validateAccess(sessionsRepository, sid, ADMINISTRATOR, KITCHENER, TABLE);
        return orderedDishesRepository.findAllByOrderId(orderId);
    }

    @GetMapping("/kitchener/{kitchenerId}/")
    public List<OrderedDish> findOrderDishesByKitchenerId(@PathVariable int kitchenerId, @CookieValue(name = SID) String sid) {
        validateAccess(sessionsRepository, sid, ADMINISTRATOR, KITCHENER);
        return orderedDishesRepository.findAllByCurrentDateAndKitchenerOrderByTimeDesc(new User(kitchenerId));
    }

    @PostMapping("/add")
    public String saveOrderedDish(@RequestBody final OrderedDish orderedDish, @CookieValue(name = SID) String sid) {
        validateAccess(sessionsRepository, sid, TABLE, ADMINISTRATOR);
        orderedDishesRepository.save(orderedDish);
        updateOrder(orderedDish);
        messagingTemplate.convertAndSend("/topic/public", orderedDish);
        return Response.SAVED.name();
    }

    @PostMapping("/remove")
    public String removeOrderedDish(@RequestBody final OrderedDish orderedDish, @CookieValue(name = SID) String sid) {
        validateAccess(sessionsRepository, sid, ADMINISTRATOR, TABLE);
        validateOrderedDish(orderedDish.getId());

        orderedDishesRepository.deleteById(orderedDish.getId());
        updateOrder(orderedDish);
        return Response.REMOVED.name();
    }

    @PostMapping("/update")
    public OrderedDish updateOrderedDishKitchenerAndStatus(@Valid @RequestBody final OrderedDish orderedDish, @CookieValue(name = SID) String sid) {
        validateAccess(sessionsRepository, sid, ADMINISTRATOR, KITCHENER, TABLE);
        validateOrderedDish(orderedDish.getId());
        orderedDishesRepository.updateStatusAndKitchener(
                orderedDish.getId(),
                orderedDish.getStatus().name(),
                orderedDish.getKitchener().getId()
        );
        updateOrder(orderedDish);
        messagingTemplate.convertAndSend("/topic/public", orderedDish);
        return orderedDish;
    }

    private void updateOrder(OrderedDish orderedDish) {
        int dishesCount = orderedDishesRepository.countByOrder(orderedDish.getOrder());
        Status status;
        if (orderedDishesRepository.countByStatus(orderedDish.getOrder(), DONE) == dishesCount) {
            status = DONE;
        } else if (orderedDishesRepository.countByStatus(orderedDish.getOrder(), WAITING) == dishesCount) {
            status = WAITING;
        } else {
            status = PROCESSING;
        }
        ordersRepository.updateStatus(orderedDish.getOrder().getId(), status.name());
    }

    private void validateOrderedDish(int id) {
        orderedDishesRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        getClass().toString(), ORDERED_DISHES_ID_COLUMN + SPACE_QUOTE + id + QUOTE));
    }
}
