package com.malevich.server.repository;

import com.malevich.server.entity.Order;
import com.malevich.server.entity.OrderedDish;
import com.malevich.server.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface OrderedDishesRepository extends JpaRepository<OrderedDish, Integer> {

//    List<OrderedDish> findAllByKitchener(User kichener);
//
//    List<OrderedDish> findAllByOrder(Order order);
//
//    List<OrderedDish> findAllByStatus(String status);

    @Modifying(clearAutomatically = true)
    @Query("update ordered_dishes od set od.status = :status where u.id = :id")
    int updateStatus(@Param("id") int id, @Param("status") String status);

    @Modifying(clearAutomatically = true)
    @Query("update ordered_dishes od set od.status = :status, od.kitchener_id = :kitchener_id where u.id = :id")
    int updateKitchenerAndStatus(@Param("id") int id, @Param("status") String status, @Param("kitchener_id")int kitchenerId);
}
