package com.zlrx.actionmonitor.jms;

import com.zlrx.actionmonitor.model.DatabaseMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DatabaseChangeListener {


    @JmsListener(destination = "database-change")
    public void receiveMessage(DatabaseMessage message) {
        log.info(message.toString());
    }


}
