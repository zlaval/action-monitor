package com.zlrx.actionmonitor.it.websocket;

import com.zlrx.actionmonitor.common.model.DatabaseMessage;
import com.zlrx.actionmonitor.common.type.DatabaseAction;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.messaging.simp.stomp.StompFrameHandler;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class JmsMessageBroadcastTest {

    @LocalServerPort
    private int port;

    @Autowired
    private MessageConverter messageConverter;

    @Autowired
    private JmsTemplate jmsTemplate;


    @Test
    public void messageSentToMqBroadcastOverWebsocket() throws ExecutionException, InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        ResultHolder resultHolder = new ResultHolder();
        StompFrameHandlerImpl frameHandler = new StompFrameHandlerImpl(latch, resultHolder);

        DatabaseMessage databaseMessage = new DatabaseMessage();
        databaseMessage.setRowId(1L);
        databaseMessage.setAction(DatabaseAction.INSERT);
        databaseMessage.setTableName("Person");
        databaseMessage.setTimestamp(LocalDateTime.now());

        WebSocketClient client = new StandardWebSocketClient();
        WebSocketStompClient stompClient = new WebSocketStompClient(client);
        stompClient.setMessageConverter(messageConverter);
        StompSessionHandler sessionHandler = new StompSessionHandlerImpl();
        ListenableFuture<StompSession> sessions = stompClient.connect("ws://localhost:" + port + "/websocket", sessionHandler);
        StompSession session = sessions.get();
        StompSession.Subscription subscription = session.subscribe("/topic/database-action", frameHandler);

        jmsTemplate.convertAndSend("database-change", databaseMessage);

        latch.await(1, TimeUnit.SECONDS);
        subscription.unsubscribe();
        DatabaseMessage received = frameHandler.getResultHolder().getReceived();
        assertEquals(databaseMessage.getRowId(), received.getRowId());
        assertEquals(databaseMessage.getTableName(), received.getTableName());
        assertEquals(databaseMessage.getAction(), received.getAction());
        assertEquals(databaseMessage.getTimestamp(), received.getTimestamp());
    }

    class StompFrameHandlerImpl implements StompFrameHandler {

        private CountDownLatch latch;
        @Getter
        private ResultHolder resultHolder;

        public StompFrameHandlerImpl(CountDownLatch latch, ResultHolder resultHolder) {
            this.latch = latch;
            this.resultHolder = resultHolder;
        }

        @Override
        public Type getPayloadType(StompHeaders headers) {
            return DatabaseMessage.class;
        }

        @Override
        public void handleFrame(StompHeaders headers, Object payload) {
            DatabaseMessage pl = (DatabaseMessage) payload;
            resultHolder.setReceived(pl);
            latch.countDown();
        }
    }

    @Getter
    @Setter
    class ResultHolder {
        private DatabaseMessage received;
    }

    class StompSessionHandlerImpl extends StompSessionHandlerAdapter {
        @Override
        public Type getPayloadType(StompHeaders headers) {
            return DatabaseMessage.class;
        }

        @Override
        public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
            super.afterConnected(session, connectedHeaders);
            log.info("Connected to Socket");
        }
    }

}
