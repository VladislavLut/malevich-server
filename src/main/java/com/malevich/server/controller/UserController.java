package com.malevich.server.controller;

import com.malevich.server.entity.User;
import com.malevich.server.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
        return this.usersRepository.findAll();
    }

    @PostMapping(value = "/{login}/")
    public Optional<User> getUserByLogin(@PathVariable String login) {
        validateUser(login);
        return this.usersRepository.findUserByLogin(login);
    }

    @PostMapping("/add")
    public ResponseEntity<?> saveUser(@RequestBody final User user) {
        if(usersRepository.findUserByLogin(user.getLogin()).isPresent()) {
            throw new UserAlreadyExistException(user.getLogin());
        }
        usersRepository.save(user);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateUser(@RequestBody final User user) {
        validateUser(user.getLogin());


        //usersRepository.save(user);
        //TODO: create update query in repository


        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/remove")
    public ResponseEntity<?> removeUserById(@RequestBody final User user) {
        validateUser(user.getLogin());
        usersRepository.deleteById(user.getId());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    private void validateUser(String login)
    {
         this.usersRepository.findUserByLogin(login)
                 .orElseThrow( () -> new UserNotFoundException(login));

    }

    //TODO: authorization

}

@ResponseStatus(HttpStatus.NOT_FOUND)
class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String login) {
        super("could not find user '" + login + "'.");
    }
}

@ResponseStatus(HttpStatus.BAD_REQUEST)
class UserAlreadyExistException extends RuntimeException {

    public UserAlreadyExistException(String login) {
        super("user with login '" + login + "' already exist.");
    }
}
