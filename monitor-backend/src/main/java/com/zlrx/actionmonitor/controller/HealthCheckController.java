package com.zlrx.actionmonitor.controller;

import com.zlrx.actionmonitor.model.Health;
import com.zlrx.actionmonitor.service.AmqHealthService;
import com.zlrx.actionmonitor.type.HealthType;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/health")
public class HealthCheckController {

    @Autowired
    private AmqHealthService healthService;

    @GetMapping
    @ApiOperation(value = "Information about application health", response = HealthType.class, tags = "App Info")
    public Health checkHealth() {
        HealthType healthType = healthService.isUp() ? HealthType.UP : HealthType.MQ_DOWN;
        return new Health(healthType);
    }

}
