package com.zlrx.actionmonitor.service;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AmqHealthServiceTest {

    @Test
    public void isUpFalseOnFirstCallThenTrueAfterSet() {
        //given
        AmqHealthService healthService = new AmqHealthService();

        //when
        boolean resultOnFirstCall = healthService.isUp();
        healthService.setHealth(true);
        boolean resultAfterSetToTrue = healthService.isUp();

        assertFalse(resultOnFirstCall);
        assertTrue(resultAfterSetToTrue);
    }


}
