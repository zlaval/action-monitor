package com.zlrx.actionmonitor.model;

import com.zlrx.actionmonitor.type.DatabaseAction;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DatabaseMessage {
    private String tableName;
    private DatabaseAction action;
    private LocalDateTime timestamp;
}
