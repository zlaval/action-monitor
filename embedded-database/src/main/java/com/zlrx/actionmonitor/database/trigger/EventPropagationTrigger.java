package com.zlrx.actionmonitor.database.trigger;

import lombok.extern.slf4j.Slf4j;
import org.h2.api.Trigger;

import java.sql.Connection;
import java.sql.SQLException;

@Slf4j
public class EventPropagationTrigger implements Trigger {

    private int type;
    private String tableName;

    @Override
    public void init(Connection conn, String schemaName, String triggerName, String tableName, boolean before, int type) throws SQLException {
        this.type = type;
        this.tableName = tableName;
        log.debug("EventPropagationTrigger created to {} for {}", tableName, type);
    }

    @Override
    public void fire(Connection conn, Object[] oldRow, Object[] newRow) throws SQLException {
//        String newRowStr = Arrays.stream(oldRow).map(Object::toString).collect(Collectors.joining(","));
//        log.info(newRowStr);
        log.info("{} on {}", type, tableName);
    }

    @Override
    public void close() throws SQLException {

    }

    @Override
    public void remove() throws SQLException {

    }
}
