package com.malevich.server.repository;

import com.malevich.server.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Optional;


public interface CommentsRepository extends JpaRepository<Comment, Integer> {

    Optional<Comment> findCommentByOrder_Id(int id);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = "UPDATE comments SET comment = :comment WHERE order_id = :order", nativeQuery = true)
    int updateComment(@Param("order") int orderId, @Param("comment") String comment);

}
