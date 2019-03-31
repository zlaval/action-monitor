package com.zlrx.actionmonitor.controller;

import com.zlrx.actionmonitor.service.AmqHealthService;
import com.zlrx.actionmonitor.type.Health;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class HealthCheckControllerTest {

    @Mock
    private AmqHealthService healthService;

    @InjectMocks
    private HealthCheckController underTest;

    @Test
    public void checkHealthReturnUp() {
        //given
        when(healthService.isUp()).thenReturn(true);

        //when
        Health result = underTest.checkHealth();

        //then
        assertEquals(Health.UP, result);
        verify(healthService, times(1)).isUp();
    }

    @Test
    public void checkHealthReturnMqDown() {
        //given
        when(healthService.isUp()).thenReturn(false);

        //when
        Health result = underTest.checkHealth();

        //then
        assertEquals(Health.MQ_DOWN, result);
        verify(healthService, times(1)).isUp();
    }


}
