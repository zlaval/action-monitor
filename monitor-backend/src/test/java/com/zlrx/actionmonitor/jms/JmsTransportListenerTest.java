package com.zlrx.actionmonitor.jms;

import com.zlrx.actionmonitor.service.AmqHealthService;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class JmsTransportListenerTest {

    private AmqHealthService healthService;
    private JmsTransportListener underTest;

    @Before
    public void init() {
        healthService = spy(new AmqHealthService());
        underTest = new JmsTransportListener(healthService);
    }

    @Test
    public void onCommandSetHealthTrue() {
        //when
        underTest.onCommand(new Object());

        //then
        assertTrue(healthService.isUp());
        verify(healthService, times(1)).setHealth(true);
    }

    @Test
    public void onExceptionSetHealthFalse() {
        //when
        underTest.onException(new IOException());

        //then
        assertFalse(healthService.isUp());
        verify(healthService, times(1)).setHealth(false);
    }

    @Test
    public void transportInteruptedSetHealthFalse() {
        //when
        underTest.transportInterupted();

        //then
        assertFalse(healthService.isUp());
        verify(healthService, times(1)).setHealth(false);
    }

    @Test
    public void transportResumedSetHealthTrue() {
        //when
        underTest.transportResumed();

        //then
        assertTrue(healthService.isUp());
        verify(healthService, times(1)).setHealth(true);
    }

}
