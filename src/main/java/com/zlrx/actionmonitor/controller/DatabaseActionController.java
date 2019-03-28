package com.zlrx.actionmonitor.controller;

import com.zlrx.actionmonitor.enums.DatabaseAction;
import com.zlrx.actionmonitor.model.DatabaseMessage;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/database-action")
public class DatabaseActionController {

    @GetMapping(produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<DatabaseMessage> streamDatabaseAction() {
        return Flux.interval(Duration.ofSeconds(2))
                .map(y -> new DatabaseMessage("name", DatabaseAction.INSERT, LocalDateTime.now()));
    }


}
