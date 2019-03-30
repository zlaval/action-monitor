package com.zlrx.actionmonitor.controller;

import com.zlrx.actionmonitor.model.ApplicationInfo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping("/api/v1/app-info")
public class AppInfoController {

    @Value("${application.version}")
    private String version;

    @Value("${application.build.timestamp}")
    private String buildTimestamp;

    private ApplicationInfo applicationInfo;

    @PostConstruct
    private void init() {
        applicationInfo = new ApplicationInfo(buildTimestamp, version);
    }

    @GetMapping
    @ResponseBody
    @ApiOperation(value = "Information about application", response = ApplicationInfo.class, tags = "App Info")
    public ResponseEntity<ApplicationInfo> getApplicationInfo() {
        return new ResponseEntity<>(applicationInfo, HttpStatus.OK);
    }

}
