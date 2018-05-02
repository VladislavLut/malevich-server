package com.malevich.server.controller;

import com.malevich.server.repository.CommentsRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.malevich.server.entity.Comment;
import com.malevich.server.http.response.status.exception.EntityAlreadyExistException;
import com.malevich.server.http.response.status.exception.EntityNotFoundException;
import com.malevich.server.http.response.status.exception.OkException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Optional;

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

    @PostMapping("/update")
    public void updateComment(@Valid @RequestBody final Comment comment) {
        this.commentsRepository.updateComment(
                comment.getId(),
                comment.getComment()
        );
    }
}
