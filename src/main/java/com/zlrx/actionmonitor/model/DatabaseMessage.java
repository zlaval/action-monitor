package com.zlrx.actionmonitor.model;

import com.zlrx.actionmonitor.enums.DatabaseAction;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class DatabaseMessage {
    private String tableName;
    private DatabaseAction action;
    private LocalDateTime timestamp;
}
