package com.zlrx.actionmonitor.controller;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class WebSocketEventControllerTest {

    private static final String SOCKET_CONNECTION_MESSAGE = "Successfully connected to server";

    @Test
    public void onSubscribeSendMessage() {
        //given
        WebSocketEventController controller = new WebSocketEventController();

        //when
        String response = controller.onSubscribe();

        //then
        assertEquals(SOCKET_CONNECTION_MESSAGE, response);
    }


}
