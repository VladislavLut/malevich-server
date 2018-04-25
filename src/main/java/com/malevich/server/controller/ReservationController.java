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

import static com.malevich.server.controller.UserController.QUOTE;
import static com.malevich.server.controller.UserController.SPACE_QUOTE;
import static com.malevich.server.http.response.status.exception.OkException.REMOVED;
import static com.malevich.server.http.response.status.exception.OkException.SAVED;

@RestController
@RequestMapping("/reserved")
public class ReservationController {

    public static final String COMA_SPACE = ", ";

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
                this.getClass().toString(),
                Reservation.DATE_COLUMN + SPACE_QUOTE + reservation.getDate() + UserController.QUOTE +
                        Reservation.TIME_COLUMN + SPACE_QUOTE + reservation.getTime() + QUOTE + COMA_SPACE +
                        Reservation.NAME_COLUMN + SPACE_QUOTE + reservation.getName() + QUOTE + COMA_SPACE +
                        Reservation.PHONE_COLUMN + SPACE_QUOTE + reservation.getPhone() + QUOTE)
        );
    }

    @PostMapping("/add")
    public void addReservation(@RequestBody Reservation reservation) {

        validateReservationForAdding(reservation);

        this.reservedRepository.save(reservation);

        throw new OkException(SAVED, this.getClass().toString());
    }

    @PostMapping("/remove")
    public void removeReservation(@RequestBody Reservation reservation) {
        validateReservation(reservation.getId());

        this.reservedRepository.deleteById(reservation.getId());

        throw new OkException(REMOVED, this.getClass().toString());
    }


    private void validateReservation(int id) {
        this.reservedRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        this.getClass().toString(), Reservation.ID_COLUMN + SPACE_QUOTE + id + QUOTE));
    }

    private void validateReservationForAdding(Reservation reservation) {
        try {
            if (this.reservedRepository
                    .findAllByDateAndTimeBetween(
                            reservation.getDate(),
                            TimeUtil.shiftTime(reservation.getTime(), -RESERVATION_RANGE),
                            TimeUtil.shiftTime(reservation.getTime(), RESERVATION_RANGE))
                    .isPresent()) {
                throw new EntityAlreadyExistException(
                        this.getClass().toString(),
                        Reservation.DATE_COLUMN + SPACE_QUOTE + reservation.getDate() + COMA_SPACE
                                + Reservation.TIME_COLUMN + SPACE_QUOTE + reservation.getTime() + QUOTE);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }


}
