package com.malevich.server.repository;

import com.malevich.server.entity.Order;
import com.malevich.server.entity.TableItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface OrdersRepository extends JpaRepository<Order, Integer> {

    Optional<Order> findOrderById(int id);

    //Optional<Order> findOrderByTable(int tableId);

//    List<Order> findAllByIdIsNotNull();

//    List<Order> findAllByDate(String date);

//    List<Order> findAllByStatus(String status);

    @Modifying(clearAutomatically = true)
    @Query("update orders o set o.status = :status where o.id = :id")
    int updateStatus(@Param("id") int id, @Param("status") String status);

}
