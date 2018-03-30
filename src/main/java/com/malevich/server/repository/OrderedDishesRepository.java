package com.malevich.server.repository;

import com.malevich.server.entity.Order;
import com.malevich.server.entity.OrderedDish;
import com.malevich.server.entity.Table;
import com.malevich.server.entity.User;
import org.springframework.core.Ordered;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderedDishesRepository extends CrudRepository<OrderedDish, Integer> {

    OrderedDish findOrderedDishById(int id);

    List<OrderedDish> findAllByIdIsNotNull();

    List<OrderedDish> findAllByKitchener(User kichener);

    List<OrderedDish> findAllByOrder(Order order);

    List<OrderedDish> findAllByStatus(String status);

}
