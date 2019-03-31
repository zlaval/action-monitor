package com.zlrx.actionmonitor.jms;

import com.zlrx.actionmonitor.common.model.DatabaseMessage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class DatabaseChangeListenerTest {

    public static final String TOPIC_NAME = "/topic/database-action";

    @Mock
    private SimpMessagingTemplate template;

    @InjectMocks
    private DatabaseChangeListener databaseChangeListener;

    @Test
    public void receiveMqMessageSendTowardSocker() {
        //given
        DatabaseMessage databaseMessage = new DatabaseMessage();
        doNothing().when(template).convertAndSend(TOPIC_NAME, databaseMessage);

        //when
        databaseChangeListener.receiveMessage(databaseMessage);

        //then
        verify(template, times(1))
                .convertAndSend("/topic/database-action", databaseMessage);
    }


}
