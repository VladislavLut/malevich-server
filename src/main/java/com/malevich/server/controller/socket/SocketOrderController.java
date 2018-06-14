package com.malevich.server.controller.socket;

import com.malevich.server.entity.Order;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class SocketOrderController
{
    @MessageMapping("/orders.sendMessage")
    @SendTo("/topic/public")
    public Order sendMessage(@Payload Order order) {
        return order;
    }

}
