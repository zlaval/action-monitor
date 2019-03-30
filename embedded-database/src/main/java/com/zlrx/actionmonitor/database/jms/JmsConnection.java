package com.zlrx.actionmonitor.database.jms;

import com.zlrx.actionmonitor.common.model.DatabaseMessage;
import com.zlrx.actionmonitor.database.serializer.ObjectSerializer;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

@Slf4j
public class JmsConnection {

    private Connection connection;
    private ObjectSerializer objectSerializer;
    private ActiveMQConnectionFactory connectionFactory;

    private JmsConnection() {
        establishConnection();
        objectSerializer = new ObjectSerializer();
    }

    private static class InstanceHolder {
        private static final JmsConnection jmsConnection = new JmsConnection();
    }

    public static JmsConnection getInstance() {
        return InstanceHolder.jmsConnection;
    }

    private void establishConnection() {
        connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
        try {
            connection = connectionFactory.createConnection();
            connection.start();
            log.debug("Jms connection established");
        } catch (JMSException e) {
            log.error("Cannot connect to jms", e);
            //TODO exception throw
        }
    }

    public void closeConnection() {
        try {
            connection.close();
            log.debug("Jms connection closed");
        } catch (JMSException e) {
            log.trace("Cannot close jms connection, it maybe closed", e);
        }
    }

    public void sendMessage(DatabaseMessage databaseMessage) {
        try {
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Destination queue = session.createQueue("database-change");
            MessageProducer producer = session.createProducer(queue);
            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
            String dbTextMessage = objectSerializer.serializeObject(databaseMessage);
            TextMessage message = session.createTextMessage(dbTextMessage);
            producer.send(message);
            session.close();
        } catch (JMSException e) {
            log.error("Cannot send jms message", e);
            //TODO exception throw
        }

    }

}
