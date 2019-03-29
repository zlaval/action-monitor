package com.zlrx.actionmonitor.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicBoolean;

@Slf4j
@Service
public class AmqHealthService {

    private AtomicBoolean up = new AtomicBoolean();

    public void setHealth(boolean up) {
        this.up.getAndSet(up);
    }

    public boolean isUp() {
        return up.get();
    }

}
