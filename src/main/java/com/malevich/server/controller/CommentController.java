package com.malevich.server.controller;

import com.malevich.server.entity.Comment;
import com.malevich.server.http.response.status.exception.EntityAlreadyExistException;
import com.malevich.server.http.response.status.exception.EntityNotFoundException;
import com.malevich.server.repository.CommentsRepository;
import com.malevich.server.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.malevich.server.controller.UserController.QUOTE;
import static com.malevich.server.controller.UserController.SPACE_QUOTE;

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
    public Comment findCommentById(@PathVariable int id) {
        validateComment(id);
        return this.commentsRepository.findById(id).orElse(new Comment(-1));
    }

    @GetMapping("/all")
    public List<Comment> findAllComments() {
        return this.commentsRepository.findAll();
    }

    @GetMapping("/order/{orderId}/")
    public Comment findCommentByOrderId(@PathVariable int orderId) {
        return this.commentsRepository.findCommentByOrderId(orderId).orElse(new Comment(-1));
    }

   @PostMapping("/add")
    public String saveComment(@RequestBody final Comment comment) {
        if (this.commentsRepository.findById(comment.getId()).isPresent()) {
            throw new EntityAlreadyExistException(
                    this.getClass().toString(), Comment.ID_COLUMN + SPACE_QUOTE + comment.getId() + QUOTE);
        }
       this.commentsRepository.save(comment);
       return Response.SAVED.name();
    }

    @PostMapping("/remove")
    public String removeComment(@RequestBody final Comment comment) {
        validateComment(comment.getId());

        commentsRepository.deleteById(comment.getId());
        return Response.REMOVED.name();
    }

    @PostMapping("/update")
    public String updateComment(@Valid @RequestBody final Comment comment) {
        validateComment(comment.getId());
        this.commentsRepository.updateById(
                comment.getOrder().getId(),
                comment.getComment()
        );
        return Response.UPDATED.name();
    }

    private void validateComment(int id) {
        this.commentsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        this.getClass().toString(), Comment.ID_COLUMN + SPACE_QUOTE + id + QUOTE));
    }
}
