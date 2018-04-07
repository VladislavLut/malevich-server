package com.malevich.server.repository;

import com.malevich.server.entity.Order;
import com.malevich.server.entity.Table;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface OrdersRepository extends CrudRepository<Order, Integer> {

    Optional<Order> findOrderById(int id);

    Optional<Order> findOrderByTable(Table table);

    List<Order> findAllByIdIsNotNull();

    List<Order> findAllByDate(String date);

    List<Order> findAllByStatus(String status);

}
