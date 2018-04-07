package com.malevich.server.repository;

import com.malevich.server.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UsersRepository extends CrudRepository<User, Integer>{

    Optional<User>findUserByLogin(String login);

    Optional<User> findUserById(int id);

    List<User> findAllByType(String type);

    List<User> findAllByIdIsNotNull();

    List<User> findAllByName(String name);
}
