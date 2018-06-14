package com.malevich.server.controller.rest;

import com.malevich.server.entity.Reservation;
import com.malevich.server.enums.Response;
import com.malevich.server.exception.EntityAlreadyExistException;
import com.malevich.server.exception.EntityNotFoundException;
import com.malevich.server.repository.ReservedRepository;
import com.malevich.server.repository.SessionsRepository;
import com.malevich.server.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.malevich.server.controller.rest.SessionController.SID;
import static com.malevich.server.controller.rest.UserController.SPACE_QUOTE;
import static com.malevich.server.util.Strings.*;
import static com.malevich.server.util.ValidationUtil.validateAccess;
import static org.apache.logging.log4j.util.Chars.QUOTE;

@RestController
@RequestMapping("/reserved")
public class ReservationController {

    public static final String COMA_SPACE = ", ";

    public final int RESERVATION_RANGE_HOURS = 2;

    private final ReservedRepository reservedRepository;

    private final SessionsRepository sessionsRepository;

    private final SimpMessageSendingOperations messagingTemplate;

    @Autowired
    public ReservationController(ReservedRepository reservedRepository, SessionsRepository sessionsRepository, SimpMessageSendingOperations messagingTemplate) {
        this.reservedRepository = reservedRepository;
        this.sessionsRepository = sessionsRepository;
        this.messagingTemplate = messagingTemplate;
    }

    @GetMapping("/all")
    public List<Reservation> findAll(@CookieValue(name = "sid") String sid) {
        validateAccess(sessionsRepository, sid, true);
        return reservedRepository.findAll();
    }

    @GetMapping("/{id}/")
    public Optional<Reservation> findReservationById(@PathVariable int id, @CookieValue(name = "sid") String sid) {
        validateAccess(sessionsRepository, sid, true);
        validateReservation(id);
        return reservedRepository.findById(id);
    }

    @PostMapping("/find")
    public List<Reservation> find(@RequestBody Reservation reservation, @CookieValue(name = "sid") String sid) {
        validateAccess(sessionsRepository, sid, true);
        if (reservation.getId() != 0) {
            validateReservation(reservation.getId());
            return Collections.singletonList(reservedRepository.findById(reservation.getId()).get());
        }

        Date date = reservation.getDate();

        return reservedRepository.findAllByDateAndTimeAndNameAndPhone(
                date == null ? new Date(System.currentTimeMillis()) : date,
                reservation.getTime(),
                reservation.getName(),
                reservation.getPhone()
        ).orElseThrow(() -> new EntityNotFoundException(
                getClass().toString(),
                RESERVED_DATE_COLUMN + SPACE_QUOTE + reservation.getDate() + QUOTE +
                        RESERVED_TIME_COLUMN + SPACE_QUOTE + reservation.getTime() + QUOTE + COMA_SPACE +
                        RESERVED_NAME_COLUMN + SPACE_QUOTE + reservation.getName() + QUOTE + COMA_SPACE +
                        RESERVED_PHONE_COLUMN + SPACE_QUOTE + reservation.getPhone() + QUOTE)
        );
    }

    @GetMapping("/{date}/date")
    public List<Reservation> findByDate(@PathVariable String date, @CookieValue(name = SID) String sid) {
        validateAccess(sessionsRepository, sid, true);
        return reservedRepository.findAllByDate(Date.valueOf(date));
    }

    @PostMapping("/add")
    public Reservation addReservation(@RequestBody Reservation reservation, @CookieValue(name = SID) String sid) {
        validateAccess(sessionsRepository, sid, true);
        validateReservationForAdding(reservation);

        reservedRepository.save(reservation);
        messagingTemplate.convertAndSend("/topic/public", reservation);
        return reservation;
    }

    @PostMapping("/remove")
    public String removeReservation(@RequestBody Reservation reservation, @CookieValue(name = "sid") String sid) {
        validateAccess(sessionsRepository, sid, true);
        validateReservation(reservation.getId());

        reservedRepository.deleteById(reservation.getId());
        return Response.REMOVED.name();
    }


    private void validateReservation(int id) {
        reservedRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        getClass().toString(), RESERVED_ID_COLUMN + SPACE_QUOTE + id + QUOTE));
    }

    private void validateReservationForAdding(Reservation reservation) {
        if (reservedRepository
                .findAllByTableItemIdAndDateAndTimeBetween(
                        reservation.getTableItem().getId(),
                        reservation.getDate(),
                        TimeUtil.shiftTime(reservation.getTime(), -RESERVATION_RANGE_HOURS),
                        TimeUtil.shiftTime(reservation.getTime(), RESERVATION_RANGE_HOURS))
                .isPresent()) {
            throw new EntityAlreadyExistException(
                    Reservation.class.getSimpleName(),
                    RESERVED_DATE_COLUMN + SPACE_QUOTE + reservation.getDate() + COMA_SPACE
                            + RESERVED_TIME_COLUMN + SPACE_QUOTE + reservation.getTime() + QUOTE);
        }
    }


}
