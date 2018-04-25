package com.malevich.server.repository;

import com.malevich.server.entity.Comment;
import com.malevich.server.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface CommentsRepository extends JpaRepository<Comment, Integer> {

    Optional<Comment> findCommentByOrder(Order order);
    Optional<Comment> findCommentByOrderId(int orderId);
   // Optional<Comment> deleteByOrder(Order order);



    @Modifying(clearAutomatically = true)
    @Query("update Comment c set c.comment = :comment where c.order = :orderItem")
    int updateComment(@Param("orderItem") int orderId, @Param("comment") String comment);

}
