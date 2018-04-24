package com.malevich.server.repository;

import com.malevich.server.entity.Reservation;
import com.malevich.server.entity.TableItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

public interface ReservedRepository extends JpaRepository<Reservation, Integer> {

    @Query("select r " +
            "from Reservation r " +
            "where r.date like %:date% " +
            "and r.time like %:time% " +
            "and r.name like %:name% " +
            "and r.phone like %:phone%")
    Optional<List<Reservation>> findAllByDateAndTimeAndNameAndPhone(
            @Param("date") String date,
            @Param("time") String time,
            @Param("name") String name,
            @Param("phone") String phone);

    Optional<List<Reservation>> findAllByDateAndTimeBetween(String date, String timeFrom, String timeTo);

}