package com.zlrx.actionmonitor.jms;

import com.zlrx.actionmonitor.service.AmqHealthService;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.transport.TransportListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class JmsTransportListener implements TransportListener {

    private AmqHealthService healthService;

    @Autowired
    public JmsTransportListener(AmqHealthService healthService) {
        this.healthService = healthService;
    }

    @Override
    public void onCommand(Object command) {
        log.trace("{}", command);
        healthService.setHealth(true);
    }

    @Override
    public void onException(IOException error) {
        log.trace("{}", error);
        healthService.setHealth(false);
    }

    @Override
    public void transportInterupted() {
        healthService.setHealth(false);
    }

    @Override
    public void transportResumed() {
        healthService.setHealth(true);
    }

}
