package com.malevich.server.controller.socket;

import com.malevich.server.entity.OrderedDish;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class SocketOrderedDishController {
    @MessageMapping("/dishes.sendMessage")
    @SendTo("/topic/public")
    public OrderedDish sendMessage(@Payload OrderedDish orderedDish) {
        return orderedDish;
    }
}
