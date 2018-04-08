package com.malevich.server.repository;

import com.malevich.server.entity.Order;
import com.malevich.server.entity.OrderedDish;
import com.malevich.server.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderedDishesRepository extends JpaRepository<OrderedDish, Integer> {

    Optional<OrderedDish> findOrderedDishById(int id);

//    List<OrderedDish> findAllByIdIsNotNull();
//
//    List<OrderedDish> findAllByKitchener(User kichener);
//
//    List<OrderedDish> findAllByOrder(Order order);
//
//    List<OrderedDish> findAllByStatus(String status);

}
