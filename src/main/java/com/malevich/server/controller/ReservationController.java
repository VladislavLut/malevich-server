package com.malevich.server.controller;

import com.malevich.server.controller.exception.EntityAlreadyExistException;
import com.malevich.server.controller.exception.EntityNotFoundException;
import com.malevich.server.entity.Reservation;
import com.malevich.server.repository.ReservedRepository;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sun.java2d.x11.X11SurfaceDataProxy;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reserved")
public class ReservationController {

    @Autowired
    private final ReservedRepository reservedRepository;

    @Autowired
    public ReservationController(ReservedRepository reservedRepository) {
        this.reservedRepository = reservedRepository;
    }

    @GetMapping("/")
    public List<Reservation> findAll() {
        return this.reservedRepository.findAll();
    }

    @GetMapping("/{id}/")
    public Optional<Reservation> findReservationById(@PathVariable int id) {
        validateReservation(id);

        return this.reservedRepository.findById(id);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addReservation(@RequestBody Reservation reservation) {
        if (this.reservedRepository.findById(reservation.getId()).isPresent()) {
            throw new EntityAlreadyExistException(this.getClass(), reservation.getId());
        }

        this.reservedRepository.save(reservation);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateReservation(Reservation reservation) {
        //TODO: create update query in ReservedRepository

        return null;
    }

    @PostMapping("/remove")
    public ResponseEntity<?> removeReservation(Reservation reservation) {
        validateReservation(reservation.getId());

        this.reservedRepository.deleteById(reservation.getId());

        return new ResponseEntity<>(HttpStatus.OK);
    }


    private void validateReservation(int id) {
        this.reservedRepository.findById(id)
                .orElseThrow( () -> new EntityNotFoundException(this.getClass(), id));
    }
}
