package com.malevich.server.controller;

import com.malevich.server.entity.User;
import com.malevich.server.repository.UsersRepository;
import org.hibernate.engine.jdbc.connections.internal.UserSuppliedConnectionProviderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private final UsersRepository usersRepository;

    @Autowired
    public UserController(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @GetMapping("/all")
    public List<User> getAllUsers() {
        //validateUser(login);
        return this.usersRepository.findAll();
    }

    @PostMapping(value = "/")
    public Optional<User> getUserByLogin(@PathVariable String login) {
        validateUser(login);
        return this.usersRepository.findUserByLogin(login);
    }

    @PostMapping("/signIn")
    public void saveUser(@RequestBody final User user) {
        usersRepository.save(user);
    }

    private void validateUser(String login)
    {
        this.usersRepository.findUserByLogin(login).orElseThrow(
                () -> new UserNotFoundException(login));
    }
}

@ResponseStatus(HttpStatus.NOT_FOUND)
class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String login) {
        super("could not find user \"" + login + "\".");
    }
}
