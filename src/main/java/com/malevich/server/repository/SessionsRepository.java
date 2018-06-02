package com.malevich.server.repository;

import com.malevich.server.entity.Session;
import com.malevich.server.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Optional;

public interface SessionsRepository extends JpaRepository<Session, Integer> {

    Optional<Session> findSessionByUserId(int userId);

    Optional<Session> findSessionBySid(String token);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "DELETE Session s WHERE s.sid = :sid")
    void deleteSession(@Param("sid") String sid);
}
