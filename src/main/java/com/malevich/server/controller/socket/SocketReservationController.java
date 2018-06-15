package com.malevich.server.controller.socket;

import com.malevich.server.entity.Reservation;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class SocketReservationController {
    @MessageMapping("/reserved.sendMessage")
    @SendTo("/topic/reservation")
    public Reservation sendMessage(@Payload Reservation reservation) {
        return reservation;
    }
}
