package com.zlrx.actionmonitor.controller;

import com.zlrx.actionmonitor.service.AmqHealthService;
import com.zlrx.actionmonitor.type.Health;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/health")
public class HealthCheckController extends BaseController {

    @Autowired
    private AmqHealthService healthService;

    @GetMapping
    public Health checkHealth() {
        return healthService.isUp() ? Health.UP : Health.MQ_DOWN;
    }

}
