package com.malevich.server.controller.socket;

import com.malevich.server.entity.TableItem;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import static com.malevich.server.util.OrderUtil.removeNotActualInfo;

@Controller
public class SocketTableController {

    @MessageMapping("/tables.sendMessage")
    @SendTo("/topic/table_item")
    public TableItem sendMessage(@Payload TableItem tableItem) {
        removeNotActualInfo(tableItem);
        return tableItem;
    }


}
