package com.malevich.server.repository;

import com.malevich.server.entity.User;
import com.malevich.server.utils.UserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

public interface UsersRepository extends JpaRepository<User, Integer> {

    Optional<User>findUserByLogin(String login);

    Optional<List<User>> findAllByType(UserType type);

    @Modifying(clearAutomatically = true)
    @Query("update User u set u.name = :name where u.id = :id")
    int updateName(@Param("id") int id, @Param("name") String name);

    @Modifying(clearAutomatically = true)
    @Query("update User u set u.password = :password where u.id = :id")
    int updatePassword(@Param("id") int id, @Param("password") String password);

    @Modifying(clearAutomatically = true)
    @Query("update User u set u.birthDay = :birth_day where u.id = :id")
    int updateBirthDay(@Param("id") int id, @Param("birth_day") String birthDay);

}
