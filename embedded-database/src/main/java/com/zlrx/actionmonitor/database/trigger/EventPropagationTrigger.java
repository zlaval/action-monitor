package com.zlrx.actionmonitor.database.trigger;

import com.zlrx.actionmonitor.common.exception.NoSuchValueException;
import com.zlrx.actionmonitor.common.model.DatabaseMessage;
import com.zlrx.actionmonitor.common.type.DatabaseAction;
import lombok.extern.slf4j.Slf4j;
import org.h2.api.Trigger;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;

@Slf4j
public class EventPropagationTrigger implements Trigger {

    private DatabaseAction action;
    private String tableName;

    @Override
    public void init(Connection conn, String schemaName, String triggerName, String tableName, boolean before, int type) throws SQLException {
        action = mapTypeToAction(type);
        this.tableName = tableName;
        log.debug("EventPropagationTrigger created to {} for {}", tableName, type);
    }

    @Override
    public void fire(Connection conn, Object[] oldRow, Object[] newRow) throws SQLException {
        log.info("{} on {}", action, tableName);
        DatabaseMessage message = createMessage();
        sendMessage(message);
    }

    @Override
    public void close() throws SQLException {

    }

    @Override
    public void remove() throws SQLException {

    }

    private DatabaseMessage createMessage() {
        LocalDateTime actionTimestamp = LocalDateTime.now();
        return new DatabaseMessage(tableName, action, actionTimestamp);
    }

    private void sendMessage(DatabaseMessage message) {
        log.debug("send message to queue");
    }

    private DatabaseAction mapTypeToAction(int type) {
        for (DatabaseAction dbAction : DatabaseAction.values()) {
            if (dbAction.getType() == type) {
                return dbAction;
            }
        }
        throw new NoSuchValueException("Not found DatabaseAction with the given type " + type);
    }

}
