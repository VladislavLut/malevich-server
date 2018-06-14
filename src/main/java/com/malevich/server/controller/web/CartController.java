package com.malevich.server.controller.web;

import com.malevich.server.entity.Dish;
import com.malevich.server.entity.Order;
import com.malevich.server.entity.OrderedDish;
import com.malevich.server.entity.TableItem;
import com.malevich.server.model.Cart;
import com.malevich.server.repository.DishesRepository;
import com.malevich.server.repository.OrderedDishesRepository;
import com.malevich.server.repository.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import static com.malevich.server.controller.rest.DishController.validateDish;
import static com.malevich.server.controller.rest.DishController.validateDishes;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    DishesRepository dishesRepository;

    @Autowired
    OrdersRepository ordersRepository;

    @Autowired
    OrderedDishesRepository orderedDishesRepository;

    @Autowired
    public CartController(final DishesRepository dishesRepository,
                          final OrdersRepository ordersRepository,
                          final OrderedDishesRepository orderedDishesRepository) {
        this.dishesRepository = dishesRepository;
        this.ordersRepository = ordersRepository;
        this.orderedDishesRepository = orderedDishesRepository;
    }

    @PostMapping("/{id}/add")
    public Cart addToCart(@RequestBody Cart cart, @PathVariable Integer id) {
        validateDish(dishesRepository, id);
        cart.add(id);
        return cart;
    }

    @PostMapping("/{id}/remove")
    public Cart removeFromCart(@RequestBody Cart cart, @PathVariable Integer id) {
        cart.remove(id);
        return cart;
    }

    @PostMapping("/sum")
    public BigDecimal countSum(@RequestBody Cart cart) {
        BigDecimal sum = new BigDecimal(0);
        validateDishes(dishesRepository, cart.getDishes().keySet());
        List<Dish> dishes = dishesRepository.findAllByIdIn(cart.getDishes().keySet()).get();
        for (Dish dish : dishes) {
            sum = sum.add(dish.getPrice().multiply(BigDecimal.valueOf(cart.getDishes().get(dish.getId()))));
        }
        return sum;
    }

    @PostMapping("/confirm")
    public void confirm(@RequestBody Cart cart) {
        validateDishes(dishesRepository, cart.getDishes().keySet());
        Date date = new Date(System.currentTimeMillis());
        List<OrderedDish> orderedDishes = createOrderedDishListFromCart(cart);
        Order order = new Order(new TableItem(-1), date.toString());
        order = ordersRepository.save(order);
        for (OrderedDish orderedDish : orderedDishes) {
            orderedDish.setOrder(order);
            orderedDish.setKitchener(null);
        }
        orderedDishesRepository.saveAll(orderedDishes);
    }

    private List<OrderedDish> createOrderedDishListFromCart(@RequestBody Cart cart) {
        Time time = new Time(System.currentTimeMillis());
        List<OrderedDish> orderedDishes = new ArrayList<>();
        for (Integer dishId : cart.getDishes().keySet()) {
            orderedDishes.add(new OrderedDish(
                    null,
                    dishId,
                    null,
                    cart.getDishes().get(dishId),
                    time.toString(),
                    null));
        }
        return orderedDishes;
    }


}
