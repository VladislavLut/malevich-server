package com.malevich.server.controller.socket.listener;


import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.util.logging.Logger;

@Component
public class NotificationWebSocketListener {

    private static Logger logger = Logger.getLogger(NotificationWebSocketListener.class.getSimpleName());

    @EventListener
    public void handleWebSocketConnectListener(SessionConnectedEvent event) {
        logger.info("Received a new notification web socket connection: " + event.getUser());
    }

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        logger.info("Client Disconnected");
    }
}
