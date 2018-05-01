package com.malevich.server.repository;

import com.malevich.server.entity.OrderedDish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface OrderedDishesRepository extends JpaRepository<OrderedDish, Integer> {

//    List<OrderedDish> findAllByKitchener(User kichener);
//
//    List<OrderedDish> findAllByOrder(Order order);
//
//    List<OrderedDish> findAllByStatus(String status);

    List<OrderedDish> findAllByOrder_Id(int id);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = "UPDATE ordered_dishes SET status = :status WHERE id = :id", nativeQuery = true)
    int updateStatus(@Param("id") int id, @Param("status") String status);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = "UPDATE ordered_dishes SET status = :status, kitchener = :kitchener WHERE id = :id",
            nativeQuery = true)
    int updateKitchenerAndStatus(
            @Param("id") int id,
            @Param("status") String status,
            @Param("kitchener") int kitchener
    );
}
