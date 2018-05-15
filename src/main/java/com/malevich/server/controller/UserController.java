package com.malevich.server.controller;

import com.malevich.server.entity.User;
import com.malevich.server.http.response.status.exception.EntityAlreadyExistException;
import com.malevich.server.http.response.status.exception.EntityNotFoundException;
import com.malevich.server.repository.UsersRepository;
import com.malevich.server.util.Response;
import com.malevich.server.util.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    public static final String QUOTE = "'";
    public static final String SPACE_QUOTE = " '";

    @Autowired
    private final UsersRepository usersRepository;

    @Autowired
    public UserController(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @GetMapping("/all")
    public List<User> getAllUsers() {
        return this.usersRepository.findAll();
    }

    @GetMapping(value = "/id/{id}/")
    public Optional<User> getUserById(@PathVariable int id) {
        validateUser(id);
        return this.usersRepository.findById(id);
    }

    @GetMapping(value = "/{login}/")
    public Optional<User> getUserByLogin(@PathVariable String login) {
        validateUser(login);
        return this.usersRepository.findUserByLogin(login);
    }

    @GetMapping(value = "/type/{type}/")
    public Optional<List<User>> getAllByType(@PathVariable String type) {
        return this.usersRepository.findAllByType(UserType.valueOf(type));
    }

    @PostMapping("/add")
    public String saveUser(@RequestBody final User user) {
        if (usersRepository.findUserByLogin(user.getLogin()).isPresent()) {
            throw new EntityAlreadyExistException(
                    this.getClass().toString(),
                    User.LOGIN_COLUMN + SPACE_QUOTE + user.getLogin() + QUOTE);
        }
        this.usersRepository.save(user);
        return Response.SAVED.name();
    }

    @PostMapping("/update")
    public String update(@RequestBody final User user) {
        validateUser(user.getLogin());
        validateUserId(user);

        this.usersRepository.update(user.getId(), user.getName(), user.getPassword(), user.getBirthDay());
        return Response.UPDATED.name();
    }

    @PostMapping("/remove")
    public String removeUser(@RequestBody final User user) {
        validateUser(user.getLogin());

        validateUserId(user);

        usersRepository.deleteById(user.getId());
        return Response.REMOVED.name();
    }

    private void validateUser(String login) {
        this.usersRepository.findUserByLogin(login)
                .orElseThrow(
                        () -> new EntityNotFoundException(
                                this.getClass().toString(), User.LOGIN_COLUMN + SPACE_QUOTE + login + QUOTE));
    }

    private void validateUser(int id) {
        this.usersRepository.findById(id)
                .orElseThrow(
                        () -> new EntityNotFoundException(
                                this.getClass().toString(), User.ID_COLUMN + SPACE_QUOTE + id + QUOTE));
    }

    void validateUserId(User user) {
        if (user.getId() == 0) {
            user.setId(this.usersRepository.findUserByLogin(user.getLogin()).get().getId());
        }
    }

    //TODO: authorization

}

