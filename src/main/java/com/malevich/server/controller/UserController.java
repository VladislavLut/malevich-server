package com.malevich.server.controller;

import com.malevich.server.controller.exception.EntityAlreadyExistException;
import com.malevich.server.controller.exception.EntityNotFoundException;
import com.malevich.server.entity.User;
import com.malevich.server.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
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
            throw new EntityAlreadyExistException(this.getClass(), user.getLogin());
        }
        usersRepository.save(user);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/update_name")
    @Transactional
    public ResponseEntity<?> updateName(@Valid @RequestBody final User user) {
        validateUser(user.getLogin());
        this.usersRepository.updateName(user.getId(), user.getName());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/update_pass")
    @Transactional
    public ResponseEntity<?> updatePassword(@Valid @RequestBody final User user) {
        validateUser(user.getLogin());
        this.usersRepository.updatePassword(user.getId(), user.getPassword());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/remove")
    public ResponseEntity<?> removeUser(@RequestBody final User user) {
        validateUser(user.getLogin());

        if (user.getId() == 0) {
            user.setId(this.usersRepository.findUserByLogin(user.getLogin()).get().getId());
        }

        usersRepository.deleteById(user.getId());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    private void validateUser(String login)
    {
         this.usersRepository.findUserByLogin(login)
                 .orElseThrow( () -> new EntityNotFoundException(this.getClass(), login));

    }

    //TODO: authorization

}

