package com.malevich.server.repository;

import com.malevich.server.entity.Comment;
import com.malevich.server.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Optional;


public interface CommentsRepository extends JpaRepository<Comment, Integer> {

    Optional<Comment> findCommentByOrder(Order order);
    Optional<Comment> findCommentByOrderId(int orderId);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = "UPDATE comments SET comment = :comment WHERE order_id = :order", nativeQuery = true)
    int updateById(@Param("order") int orderId, @Param("comment") String comment);

}
