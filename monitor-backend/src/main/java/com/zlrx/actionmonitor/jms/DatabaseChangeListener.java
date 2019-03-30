package com.zlrx.actionmonitor.jms;

import com.zlrx.actionmonitor.common.model.DatabaseMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DatabaseChangeListener {

    @Autowired
    private SimpMessagingTemplate template;

    @JmsListener(destination = "database-change")
    public void receiveMessage(DatabaseMessage message) {
        log.info("New message was received from the queue: {}, broadcast it to the connected clients", message);
        template.convertAndSend("/topic/database-action", message);
    }

}
