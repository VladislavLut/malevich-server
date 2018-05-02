package com.malevich.server.repository;

import com.malevich.server.entity.Order;
import com.malevich.server.entity.TableItem;
import com.malevich.server.utils.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface OrdersRepository extends JpaRepository<Order, Integer> {


    //Optional<Order> findOrderByTable(int tableId);

    //List<Order> findAllByIdIsNotNull();

    //Optional<Order> findFirstByTableAndStatusIgnoreCase(int tableId, String status);

    List<Order> findAllByDate(String date);

    List<Order> findAllByStatus(Status status);

    List<Order> findAllByStatusNotLike(Status status);

    Optional<Order> findFirstByTableItemAndStatusNotLike(TableItem tableId, Status status);

    @Modifying(clearAutomatically = true)
    @Query("update Order o set o.status = :status where o.id = :id")
    int updateById(@Param("id") int id, @Param("status") Status status);


}
