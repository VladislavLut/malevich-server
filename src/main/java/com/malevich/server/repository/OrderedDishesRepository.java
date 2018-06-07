package com.malevich.server.repository;

import com.malevich.server.entity.Order;
import com.malevich.server.entity.OrderedDish;
import com.malevich.server.util.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface OrderedDishesRepository extends JpaRepository<OrderedDish, Integer> {

    List<OrderedDish> findAllByKitchenerId(int kitchenerId);
    List<OrderedDish> findAllByOrderId(int orderId);
    List<OrderedDish> findAllByStatus(Status status);
    
    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = "UPDATE ordered_dishes SET status = :status, kitchener_id = :kitchener WHERE id = :id",
            nativeQuery = true)
    int updateStatusAndKitchener(
            @Param("id") int id,
            @Param("status") String status,
            @Param("kitchener") int kitchener
    );

    @Transactional
    @Query("select count(o) from OrderedDish o where o.order =:order and o.status = :status")
    int countByStatus(@Param("order") Order order, @Param("status") Status status);

    @Transactional
    @Query("select count(o) from OrderedDish o where o.order =:order")
    int countByOrder(@Param("order") Order order);
}
