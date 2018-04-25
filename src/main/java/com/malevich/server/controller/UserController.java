package com.malevich.server.controller;

import com.malevich.server.entity.User;
import com.malevich.server.http.response.status.exception.EntityAlreadyExistException;
import com.malevich.server.http.response.status.exception.EntityNotFoundException;
import com.malevich.server.http.response.status.exception.OkException;
import com.malevich.server.repository.UsersRepository;
import com.malevich.server.utils.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

import static com.malevich.server.http.response.status.exception.OkException.*;

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

    @GetMapping(value = "/{login}/")
    public Optional<User> getUserByLogin(@PathVariable String login) {
        validateUser(login);
        return this.usersRepository.findUserByLogin(login);
    }

    @GetMapping(value = "/{type}")
    public Optional<List<User>> getAllByType(@PathVariable String type) {
        return this.usersRepository.findAllByType(UserType.valueOf(type));
    }

    @PostMapping("/add")
    public void saveUser(@RequestBody final User user) throws OkException {
        if(usersRepository.findUserByLogin(user.getLogin()).isPresent()) {
            throw new EntityAlreadyExistException(
                    this.getClass().toString(),
                    User.LOGIN_COLUMN + SPACE_QUOTE +  user.getLogin() + QUOTE);
        }
        usersRepository.save(user);

        throw new OkException(SAVED, this.getClass().toString());
    }

    @PostMapping("/update-name")
    @Transactional
    public void updateName(@Valid @RequestBody final User user) {
        validateUser(user.getLogin());
        this.usersRepository.updateName(user.getId(), user.getName());

        throw new OkException(UPDATED, this.getClass().toString(), User.NAME_COLUMN);
    }

    @PostMapping("/update-pass")
    @Transactional
    public void updatePassword(@Valid @RequestBody final User user) {
        validateUser(user.getLogin());
        this.usersRepository.updatePassword(user.getId(), user.getPassword());

        throw new OkException(UPDATED, this.getClass().toString(), User.PASSWORD_COLUMN);
    }

    @PostMapping("/update-birth-day")
    @Transactional
    public void updateBirthDay(@Valid @RequestBody final User user) {
        validateUser(user.getLogin());
        this.usersRepository.updateBirthDay(user.getId(), user.getBirthDay());

        throw new OkException(UPDATED, this.getClass().toString(), User.BIRTH_DAY_COLUMN);
    }

    @PostMapping("/remove")
    public void removeUser(@RequestBody final User user) {
        validateUser(user.getLogin());

        if (user.getId() == 0) {
            user.setId(this.usersRepository.findUserByLogin(user.getLogin()).get().getId());
        }

        usersRepository.deleteById(user.getId());

        throw new OkException(REMOVED, this.getClass().toString());
    }

    private void validateUser(String login)
    {
         this.usersRepository.findUserByLogin(login)
                 .orElseThrow(
                         () -> new EntityNotFoundException(
                                 this.getClass().toString(), User.LOGIN_COLUMN + SPACE_QUOTE + login + QUOTE));
    }

    //TODO: authorization

}

