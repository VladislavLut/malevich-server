package com.malevich.server.controller.socket;

import com.malevich.server.model.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class NotificationController
{
    @MessageMapping("/notifications.sendMessage")
    @SendTo("/topic/notifications")
    public Message sendMessage(@Payload Message message) {
        return message;
    }

}
