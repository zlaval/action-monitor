package com.zlrx.actionmonitor.database.trigger;

import com.zlrx.actionmonitor.common.exception.TechnicalException;
import com.zlrx.actionmonitor.common.model.DatabaseMessage;
import com.zlrx.actionmonitor.common.type.DatabaseAction;
import com.zlrx.actionmonitor.database.jms.ChangeMessageProducer;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.sql.SQLException;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class EventPropagationTriggerTest {

    private ChangeMessageProducer changeMessageProducer;
    private EventPropagationTrigger underTest;

    @Before
    public void init() {
        changeMessageProducer = mock(ChangeMessageProducer.class);
        doNothing().when(changeMessageProducer).sendMessage(Mockito.any(DatabaseMessage.class));
        underTest = new EventPropagationTrigger(changeMessageProducer);
    }

    @Test
    public void initWithProperTypeAndFireEventSuccessfully() throws SQLException {
        //given
        underTest.init(null, "", "trigger", "table", false, DatabaseAction.INSERT.getType());

        //when
        underTest.fire(null, null, new Object[]{1L});

        //then
        verify(changeMessageProducer, times(1)).sendMessage(Mockito.any(DatabaseMessage.class));
    }

    @Test(expected = TechnicalException.class)
    public void intiThrowErrorOnUnknownType() throws SQLException {
        //when
        underTest.init(null, "", "trigger", "table", false, 3);
    }

}
