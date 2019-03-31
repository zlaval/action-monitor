package com.zlrx.actionmonitor.database.trigger;

import com.zlrx.actionmonitor.common.exception.TechnicalException;
import com.zlrx.actionmonitor.common.model.DatabaseMessage;
import com.zlrx.actionmonitor.common.type.DatabaseAction;
import com.zlrx.actionmonitor.database.jms.ChangeMessageProducer;
import lombok.extern.slf4j.Slf4j;
import org.h2.api.Trigger;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;

@Slf4j
public class EventPropagationTrigger implements Trigger {

    private static final int ID_COLUMN_INDEX = 0;

    private String tableName;
    private DatabaseAction action;
    private ChangeMessageProducer changeMessageProducer = ChangeMessageProducer.getInstance();

    @Override
    public void init(Connection conn, String schemaName, String triggerName, String tableName, boolean before, int type) throws SQLException {
        action = mapTypeToAction(type);
        this.tableName = tableName;
        log.debug("EventPropagationTrigger created to {} for {}", tableName, type);
    }

    @Override
    public void fire(Connection conn, Object[] oldRow, Object[] newRow) throws SQLException {
        log.info("{} on {}", action, tableName);
        Long id = getId(oldRow, newRow);
        DatabaseMessage message = createMessage(id);
        sendMessage(message);
    }

    @Override
    public void close() throws SQLException {
        changeMessageProducer.closeConnection();
    }

    @Override
    public void remove() throws SQLException {
        /*no action required*/
    }

    private Long getId(Object[] oldRow, Object[] newRow) {
        if (action == DatabaseAction.INSERT) {
            return (Long) newRow[ID_COLUMN_INDEX];
        } else {
            return (Long) oldRow[ID_COLUMN_INDEX];
        }
    }

    private DatabaseMessage createMessage(Long id) {
        LocalDateTime actionTimestamp = LocalDateTime.now();
        return new DatabaseMessage(id, tableName, action, actionTimestamp);
    }

    private void sendMessage(DatabaseMessage message) {
        log.debug("send message to queue");
        changeMessageProducer.sendMessage(message);
    }

    private DatabaseAction mapTypeToAction(int type) {
        for (DatabaseAction dbAction : DatabaseAction.values()) {
            if (dbAction.getType() == type) {
                return dbAction;
            }
        }
        throw new TechnicalException("Not found DatabaseAction with the given type " + type);
    }

}
