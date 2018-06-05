package com.malevich.server.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.malevich.server.entity.User;
import com.malevich.server.exception.EntityAlreadyExistException;
import com.malevich.server.exception.EntityNotFoundException;
import com.malevich.server.repository.SessionsRepository;
import com.malevich.server.repository.UsersRepository;
import com.malevich.server.util.Response;
import com.malevich.server.util.UserType;
import com.malevich.server.view.Views;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Optional;

import static com.malevich.server.controller.SessionController.SID;
import static com.malevich.server.entity.User.LOGIN_COLUMN;
import static com.malevich.server.util.EncodeUtil.encodePassword;
import static com.malevich.server.util.UserType.*;
import static com.malevich.server.util.ValidationUtil.validateAccess;
import static org.apache.logging.log4j.util.Chars.QUOTE;

@RestController
@RequestMapping("/users")
public class UserController {

    public static final String SPACE_QUOTE = " '";

    @Autowired
    private final UsersRepository usersRepository;

    @Autowired
    private final SessionsRepository sessionsRepository;

    @Autowired
    public UserController(UsersRepository usersRepository, SessionsRepository sessionsRepository) {
        this.usersRepository = usersRepository;
        this.sessionsRepository = sessionsRepository;
    }

    @JsonView(Views.Public.class)
    @GetMapping("/all")
    public List<User> getAllUsers(@CookieValue(name = SID) String sid) {
        validateAccess(sessionsRepository, sid, ADMINISTRATOR);
        return this.usersRepository.findAll();
    }

    @JsonView(Views.Public.class)
    @GetMapping(value = "/id/{id}/")
    public Optional<User> getUserById(@PathVariable int id, @CookieValue(name = SID) String sid) {
        validateAccess(sessionsRepository, sid, ADMINISTRATOR, CLIENT, KITCHENER, TABLE);
        return this.usersRepository.findById(id);
    }

    @JsonView(Views.Public.class)
    @GetMapping(value = "/{login}/")
    public Optional<User> getUserByLogin(@PathVariable String login, @CookieValue(name = SID) String sid) {
        validateAccess(sessionsRepository, sid, ADMINISTRATOR, CLIENT, KITCHENER, TABLE);
        return this.usersRepository.findUserByLogin(login);
    }

    @JsonView(Views.Public.class)
    @GetMapping(value = "/type/{type}/")
    public Optional<List<User>> getAllByType(@PathVariable String type, @CookieValue(name = SID) String sid) {
        validateAccess(sessionsRepository, sid, ADMINISTRATOR);
        return this.usersRepository.findAllByType(UserType.valueOf(type));
    }

    @PostMapping("/add")
    public String saveUser(@RequestBody final User user, @CookieValue(name = SID) String sid) {
        validateAccess(sessionsRepository, sid, true);
        if (usersRepository.findUserByLogin(user.getLogin()).isPresent()) {
            throw new EntityAlreadyExistException(
                    this.getClass().toString(),
                    LOGIN_COLUMN + SPACE_QUOTE + user.getLogin() + QUOTE);
        }
        user.setPassword(encodePassword(user));
        this.usersRepository.save(user);
        return Response.SAVED.name();
    }

    @PostMapping("/update")
    public String update(@RequestBody final User user, @CookieValue(name = SID) String sid) {
        validateAccess(sessionsRepository, sid, ADMINISTRATOR, CLIENT, KITCHENER);
        validateUser(user);
        user.setPassword(encodePassword(user));

        this.usersRepository.update(user.getId(), user.getName(), user.getPassword(), user.getBirthDay());
        return Response.UPDATED.name();
    }

    @PostMapping("/remove")
    public String removeUser(@RequestBody final User user, @CookieValue(name = SID) String sid) {
        validateAccess(sessionsRepository, sid, CLIENT, ADMINISTRATOR, KITCHENER);
        validateUser(user);

        usersRepository.deleteById(user.getId());
        return Response.REMOVED.name();
    }

    private void validateUser(User user) {
        User origin = this.usersRepository.findUserByLogin(user.getLogin()).orElseThrow(() -> new EntityNotFoundException(
                User.class.getName(), LOGIN_COLUMN + SPACE_QUOTE + user.getLogin() + QUOTE));
        user.setId(origin.getId());
    }

}

