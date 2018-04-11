package com.malevich.server.repository;

import com.malevich.server.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

public interface UsersRepository extends JpaRepository<User, Integer> {

      Optional<User>findUserByLogin(String login);

//    Optional<User> findUserById(int id);
//
//    List<User> findAllByType(String type);

    //List<User> findAllByIdIsNotNull();

//    List<User> findAllByName(String name);

    @Modifying(clearAutomatically = true)
    @Query("update User u set u.name = :name where u.id = :id")
    int updateName(@Param("id") int id, @Param("name") String name);

    @Modifying(clearAutomatically = true)
    @Query("update User u set u.password = :password where u.id = :id")
    int updatePassword(@Param("id") int id, @Param("password") String password);

}
