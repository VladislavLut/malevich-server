package com.malevich.server.repository;

import com.malevich.server.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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

    Optional<List<Reservation>> findAllByTableItemIdAndDateAndTimeBetween(Integer tableID, Date date, Time timeFrom, Time timeTo);

    Optional<List<Reservation>> findAllByDateAndTableItemId(Date date, int id);

    List<Reservation> findAllByDate(Date date);

}