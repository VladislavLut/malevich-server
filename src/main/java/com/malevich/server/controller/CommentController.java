package com.malevich.server.controller;

import com.malevich.server.entity.Order;
import com.malevich.server.repository.CommentsRepository;
import com.malevich.server.repository.OrdersRepository;
import org.hibernate.query.Query;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.malevich.server.entity.Comment;
import com.malevich.server.http.response.status.exception.EntityAlreadyExistException;
import com.malevich.server.http.response.status.exception.EntityNotFoundException;
import com.malevich.server.http.response.status.exception.OkException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private final CommentsRepository commentsRepository;

    @Autowired
    public CommentController(CommentsRepository commentsRepository) {
        this.commentsRepository = commentsRepository;
    }

    @GetMapping("/{id}/")
    public Optional<Comment> findCommentById(@PathVariable int id) {
        return this.commentsRepository.findById(id);
    }

    @GetMapping("/all")
    public List<Comment> findAllComments() {
        return this.commentsRepository.findAll();
    }

    @GetMapping("/find/{orderId}/")
    public Optional<Comment> findCommentByOrderId(@PathVariable int orderId) {
        return this.commentsRepository.findCommentByOrderId(orderId);
    }

    @PostMapping("/find-by-order")
    public Optional<Comment> findCommentByOrder(@RequestBody final Order order) {
        return this.commentsRepository.findCommentByOrder(order);
    }

    @PostMapping("/update")
    @Transactional
    public void updateComment(@Valid @RequestBody final Comment comment) {
        this.commentsRepository.updateComment(
                comment.getId(),
                comment.getComment()
        );
        throw new OkException("comment was updated");
    }

   @PostMapping("/add")
    public void saveComment(@RequestBody final Comment comment) {
        if (this.commentsRepository.findById(comment.getId()).isPresent()) {
            throw new EntityAlreadyExistException(this.getClass(), "id '" + comment.getId() + "'");
        }
        this.commentsRepository.save(comment);

        throw new OkException("comment saved in the database");
    }
/*
    @PostMapping("/remove-by-order")
    public void removeByOrder(@RequestBody final Order order) {

        commentsRepository.deleteByOrder(order);

        throw new OkException("comment removed from the database");
    }
*/
    @PostMapping("/remove")
    public void removeComment(@RequestBody final Comment comment) {

        commentsRepository.deleteById(comment.getId());

        throw new OkException("comment removed from the database");
    }

    private void validateComment(int id) {
        this.commentsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(this.getClass(), "id '" + id + "'."));
    }
}
