package com.malevich.server.controller;

import com.malevich.server.entity.Order;
import com.malevich.server.repository.CommentsRepository;
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

import static com.malevich.server.controller.UserController.QUOTE;
import static com.malevich.server.controller.UserController.SPACE_QUOTE;
import static com.malevich.server.http.response.status.exception.OkException.REMOVED;
import static com.malevich.server.http.response.status.exception.OkException.SAVED;
import static com.malevich.server.http.response.status.exception.OkException.UPDATED;

@RestController
@RequestMapping("/comments")
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

    @GetMapping("/all-comments")
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

   @PostMapping("/add")
    public void saveComment(@RequestBody final Comment comment) {
        if (this.commentsRepository.findById(comment.getId()).isPresent()) {
            throw new EntityAlreadyExistException(
                    this.getClass().toString(), Comment.ID_COLUMN + SPACE_QUOTE + comment.getId() + QUOTE);
        }
       this.commentsRepository.save(comment);

       throw new OkException(SAVED, this.getClass().toString());
    }

    @PostMapping("/remove")
    public void removeComment(@RequestBody final Comment comment) {
        validateComment(comment.getId());

        commentsRepository.deleteById(comment.getId());

        throw new OkException(REMOVED, this.getClass().toString());
    }

    @PostMapping("/update")
    public void updateComment(@Valid @RequestBody final Comment comment) {
        validateComment(comment.getId());
        this.commentsRepository.updateById(
                comment.getOrder().getId(),
                comment.getComment()
        );

        throw new OkException(
                UPDATED,
                this.getClass().toString(),
                Order.ID_COLUMN + SPACE_QUOTE + comment.getId() + QUOTE);
    }

    private void validateComment(int id) {
        this.commentsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        this.getClass().toString(), Comment.ID_COLUMN + SPACE_QUOTE + id + QUOTE));
    }
}
