package com.zlrx.actionmonitor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@EnableJms
@SpringBootApplication
public class ActionMonitorApplication {

    public static void main(String[] args) {
        SpringApplication.run(ActionMonitorApplication.class, args);
    }

}
