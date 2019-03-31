package com.zlrx.actionmonitor.common.model;

import com.zlrx.actionmonitor.common.type.DatabaseAction;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DatabaseMessage implements Serializable {
    private Long rowId;
    private String tableName;
    private DatabaseAction action;
    private LocalDateTime timestamp;
}
