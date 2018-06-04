package com.malevich.server.repository;

import com.malevich.server.entity.Reservation;
import com.malevich.server.entity.TableItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

import javax.transaction.Transactional;
import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Optional;

public interface ReservedRepository extends JpaRepository<Reservation, Integer> {

    @Transactional
    @Query("select r " +
            "from Reservation r " +
            "where r.date = :date " +
            "and r.time = :time " +
            "and r.name like %:name% " +
            "and r.phone like %:phone%")
    Optional<List<Reservation>> findAllByDateAndTimeAndNameAndPhone(
            @Param("date") Date date,
            @Param("time") Time time,
            @Param("name") String name,
            @Param("phone") String phone);

    Optional<List<Reservation>> findAllByDateAndTimeBetween(Date date, Time timeFrom, Time timeTo);

}