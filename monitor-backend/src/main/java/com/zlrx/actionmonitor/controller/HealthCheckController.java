package com.zlrx.actionmonitor.controller;

import com.zlrx.actionmonitor.service.AmqHealthService;
import com.zlrx.actionmonitor.type.Health;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api("Application health information")
@RestController
@RequestMapping("/api/v1/health")
public class HealthCheckController {

    @Autowired
    private AmqHealthService healthService;

    @GetMapping
    @ApiOperation(value = "Information about application health", response = Health.class, tags = "App Info")
    public Health checkHealth() {
        return healthService.isUp() ? Health.UP : Health.MQ_DOWN;
    }

}
