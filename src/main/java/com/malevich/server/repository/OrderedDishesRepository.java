package com.malevich.server.repository;

import com.malevich.server.entity.Order;
import com.malevich.server.entity.OrderedDish;
import com.malevich.server.entity.User;
import com.malevich.server.utils.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface OrderedDishesRepository extends JpaRepository<OrderedDish, Integer> {

    List<OrderedDish> findAllByKitchener(User kitchener);
    List<OrderedDish> findAllByKitchenerId(int kitchenerId);

    List<OrderedDish> findAllByOrder(Order order);
    List<OrderedDish> findAllByOrderId(int orderId);

    List<OrderedDish> findAllByStatus(Status status);
    
    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = "UPDATE ordered_dishes SET status = :status WHERE id = :id", nativeQuery = true)
    int updateById(@Param("id") int id, @Param("status") String status);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = "UPDATE ordered_dishes SET status = :status, kitchener_id = :kitchener WHERE id = :id",
            nativeQuery = true)
    int updateById(
            @Param("id") int id,
            @Param("status") String status,
            @Param("kitchener") int kitchener
    );
}
