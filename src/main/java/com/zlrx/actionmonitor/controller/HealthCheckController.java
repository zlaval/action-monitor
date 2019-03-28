package com.zlrx.actionmonitor.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health")
public class HealthCheckController extends BaseController {

    @GetMapping
    public String checkHealth() {
        //TODO,MQ DONW ETC
        return "OK";
    }

}
