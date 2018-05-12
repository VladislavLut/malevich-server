package com.malevich.server.repository;

import com.malevich.server.entity.User;
import com.malevich.server.utils.UserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface UsersRepository extends JpaRepository<User, Integer> {

    Optional<User> findUserByLogin(String login);

    Optional<List<User>> findAllByType(UserType type);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = "UPDATE users SET name = :user_name, password = :password, birth_day = :birth_day WHERE id = :id"
            , nativeQuery = true)
    int update(@Param("id") int id,
               @Param("user_name") String name,
               @Param("password") String password,
               @Param("birth_day") Date birthDay);

}
