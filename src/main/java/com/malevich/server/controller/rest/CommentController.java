package com.malevich.server.controller.rest;

import com.malevich.server.entity.Comment;
import com.malevich.server.enums.Response;
import com.malevich.server.exception.EntityAlreadyExistException;
import com.malevich.server.exception.EntityNotFoundException;
import com.malevich.server.repository.CommentsRepository;
import com.malevich.server.repository.SessionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.malevich.server.controller.rest.SessionController.SID;
import static com.malevich.server.controller.rest.UserController.SPACE_QUOTE;
import static com.malevich.server.enums.UserType.ADMINISTRATOR;
import static com.malevich.server.enums.UserType.TABLE;
import static com.malevich.server.util.Strings.COMMENTS_ID_COLUMN;
import static com.malevich.server.util.ValidationUtil.validateAccess;
import static org.apache.logging.log4j.util.Chars.QUOTE;

@RestController
@RequestMapping("/comments")
public class CommentController {

    private final CommentsRepository commentsRepository;

    private final SessionsRepository sessionsRepository;

    @Autowired
    public CommentController(CommentsRepository commentsRepository, SessionsRepository sessionsRepository) {
        this.commentsRepository = commentsRepository;
        this.sessionsRepository = sessionsRepository;
    }

    @GetMapping("/{id}/")
    public Comment findCommentById(@PathVariable int id, @CookieValue(name = SID) String sid) {
        validateAccess(sessionsRepository, sid, true);
        validateComment(id);
        return commentsRepository.findById(id).orElse(new Comment(-1));
    }

    @GetMapping("/all")
    public List<Comment> findAllComments(@CookieValue(name = SID) String sid) {
        validateAccess(sessionsRepository, sid, true);
        return commentsRepository.findAll();
    }

    @GetMapping("/order/{orderId}/")
    public Comment findCommentByOrderId(@PathVariable int orderId, @CookieValue(name = SID) String sid) {
        validateAccess(sessionsRepository, sid, true);
        return commentsRepository.findCommentByOrderId(orderId).orElse(new Comment(-1));
    }

    @PostMapping("/add")
    public String saveComment(@RequestBody final Comment comment, @CookieValue(name = SID) String sid) {
        validateAccess(sessionsRepository, sid, TABLE);
        if (commentsRepository.findById(comment.getId()).isPresent()) {
            throw new EntityAlreadyExistException(
                    getClass().toString(), COMMENTS_ID_COLUMN + SPACE_QUOTE + comment.getId() + QUOTE);
        }
        commentsRepository.save(comment);
        return Response.SAVED.name();
    }

    @PostMapping("/remove")
    public String removeComment(@RequestBody final Comment comment, @CookieValue(name = SID) String sid) {
        validateAccess(sessionsRepository, sid, TABLE, ADMINISTRATOR);
        validateComment(comment.getId());

        commentsRepository.deleteById(comment.getId());
        return Response.REMOVED.name();
    }

    @PostMapping("/update")
    public String updateComment(@Valid @RequestBody final Comment comment, @CookieValue(name = SID) String sid) {
        validateAccess(sessionsRepository, sid, TABLE, ADMINISTRATOR);
        validateComment(comment.getId());
        commentsRepository.updateById(
                comment.getOrder().getId(),
                comment.getComment()
        );
        return Response.UPDATED.name();
    }

    private void validateComment(int id) {
        commentsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        getClass().toString(), COMMENTS_ID_COLUMN + SPACE_QUOTE + id + QUOTE));
    }
}
