package com.malevich.server.controller;

import com.malevich.server.entity.Reservation;
import com.malevich.server.http.response.status.exception.EntityAlreadyExistException;
import com.malevich.server.http.response.status.exception.EntityNotFoundException;
import com.malevich.server.http.response.status.exception.OkException;
import com.malevich.server.repository.ReservedRepository;
import com.malevich.server.utils.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reserved")
public class ReservationController {

    public final int RESERVATION_RANGE = 2;

    @Autowired
    private final ReservedRepository reservedRepository;

    @Autowired
    public ReservationController(ReservedRepository reservedRepository) {
        this.reservedRepository = reservedRepository;
    }

    @GetMapping("/all")
    public List<Reservation> findAll() {
        return this.reservedRepository.findAll();
    }

    @GetMapping("/{id}/")
    public Optional<Reservation> findReservationById(@PathVariable int id) {
        validateReservation(id);

        return this.reservedRepository.findById(id);
    }

    @PostMapping("/find")
    public List<Reservation> find(@RequestBody Reservation reservation) {
        if (reservation.getId() != 0) {
            validateReservation(reservation.getId());
            return Collections.singletonList(this.reservedRepository.findById(reservation.getId()).get());
        }

        return this.reservedRepository.findAllByDateAndTimeAndNameAndPhone(
                reservation.getDate(),
                reservation.getTime(),
                reservation.getName(),
                reservation.getPhone()
        ).orElseThrow(() -> new EntityNotFoundException(
                reservation,
                "date '" + reservation.getDate() + "', " +
                        "time '" + reservation.getTime() + "', " +
                        "name '" + reservation.getName() + "', " +
                        "phone '" + reservation.getPhone() + "'.")
        );
    }

    @PostMapping("/add")
    public void addReservation(@RequestBody Reservation reservation) {

        validateReservationForAdding(reservation);

        this.reservedRepository.save(reservation);

        throw new OkException("reservation saved in the database");
    }

    @PostMapping("/remove")
    public void removeReservation(@RequestBody Reservation reservation) {
        validateReservation(reservation.getId());

        this.reservedRepository.deleteById(reservation.getId());

        throw new OkException("reservation removed from the database");
    }


    private void validateReservation(int id) {
        this.reservedRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(this.getClass(), "id '" + id + "'."));
    }

    private void validateReservationForAdding(Reservation reservation) {
        TimeUtil timeUtil = new TimeUtil();
        try {
            if (this.reservedRepository
                    .findAllByDateAndTimeBetween(
                            reservation.getDate(),
                            timeUtil.shiftTime(reservation.getTime(), -RESERVATION_RANGE),
                            timeUtil.shiftTime(reservation.getTime(), RESERVATION_RANGE))
                    .isPresent()) {
                throw new EntityAlreadyExistException(
                        reservation, "date '" + reservation.getDate()
                        + "', time '" + reservation.getTime() + "'.");
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }




}
