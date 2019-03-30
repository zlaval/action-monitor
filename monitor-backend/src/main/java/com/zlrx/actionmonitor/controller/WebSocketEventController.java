package com.zlrx.actionmonitor.controller;

import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketEventController {

    @SubscribeMapping("/topic/database-action")
    public String onSubscribe() {
        return "Successfully connected to server";
    }

}
