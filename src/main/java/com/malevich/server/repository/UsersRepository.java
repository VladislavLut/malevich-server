package com.malevich.server.repository;

import com.malevich.server.entity.User;
import com.malevich.server.utils.UserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface UsersRepository extends JpaRepository<User, Integer> {

    Optional<User> findUserByLogin(String login);

    Optional<List<User>> findAllByType(UserType type);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = "UPDATE users SET name = :user_name WHERE id = :id", nativeQuery = true)
    int updateName(@Param("id") int id, @Param("user_name") String name);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = "UPDATE users SET password = :password WHERE id = :id", nativeQuery = true)
    int updatePassword(@Param("id") int id, @Param("password") String password);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = "UPDATE users SET birth_day = :birth_day WHERE id = :id", nativeQuery = true)
    int updateBirthDay(@Param("id") int id, @Param("birth_day") String birthDay);

}
