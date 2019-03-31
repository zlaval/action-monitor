package com.zlrx.actionmonitor.jms;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;

public class JmsListenerConfigTest {

    private JmsListenerConfig underTest;

    @Before
    public void init() {
        underTest = new JmsListenerConfig();
    }

    @Test
    public void messageHandlerMethodFactoryCreateInstance() {
        //when
        DefaultMessageHandlerMethodFactory factory = underTest.messageHandlerMethodFactory();

        //then
        Assert.assertNotNull(factory);
    }

}
