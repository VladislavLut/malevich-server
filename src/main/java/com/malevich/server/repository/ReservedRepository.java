package com.malevich.server.repository;

import com.malevich.server.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReservedRepository extends JpaRepository<Reservation, Integer> {

    Optional<Reservation> findReservationById(int id);

//    Optional<Reservation> findByPhoneAndDate(String phone, String date);
//
//    List<Reservation> findAllByNameAndDate(String name, String date);
//
//    List<Reservation> findAllByDateAndTime(String date, String time);
//
//    List<Reservation> findAllByDate(String date);


}
