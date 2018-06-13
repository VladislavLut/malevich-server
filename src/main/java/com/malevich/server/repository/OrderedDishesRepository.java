package com.malevich.server.repository;

import com.malevich.server.entity.Order;
import com.malevich.server.entity.OrderedDish;
import com.malevich.server.entity.User;
import com.malevich.server.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface OrderedDishesRepository extends JpaRepository<OrderedDish, Integer> {

    List<OrderedDish> findAllByOrderId(int orderId);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = "UPDATE ordered_dishes SET status = :status, kitchener_id = :kitchener WHERE id = :id",
            nativeQuery = true)
    int updateStatusAndKitchener(
            @Param("id") Integer id,
            @Param("status") String status,
            @Param("kitchener") Integer kitchener
    );

    @Transactional
    @Query("select count(o) from OrderedDish o where o.order =:order and o.status = :status")
    int countByStatus(@Param("order") Order order, @Param("status") Status status);

    @Transactional
    @Query("select count(o) from OrderedDish o where o.order =:order")
    int countByOrder(@Param("order") Order order);

    @Transactional
    @Query(value = "select od from OrderedDish od " +
            "inner join od.order o " +
            "where o.date = current_date " +
            "and od.kitchener = :kitchener " +
            "order by od.time desc")
    List<OrderedDish> findAllByCurrentDateAndKitchenerOrderByTimeDesc(@Param("kitchener") User kichener);

    @Transactional
    @Query(value = "select od from OrderedDish od " +
            "inner join od.order o " +
            "where o.date = current_date " +
            "and od.status = :status " +
            "order by od.time desc")
    List<OrderedDish> findAllByCurrentDateAndStatusOrderByTimeDesc(@Param("status") Status status);
}
