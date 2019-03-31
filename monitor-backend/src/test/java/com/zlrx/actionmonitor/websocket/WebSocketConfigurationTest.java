package com.zlrx.actionmonitor.websocket;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.StompWebSocketEndpointRegistration;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class WebSocketConfigurationTest {

    private WebSocketConfiguration underTest;

    @Before
    public void init() {
        underTest = new WebSocketConfiguration();
    }

    @Test
    public void registerStompEndpointsAddEndpoint() {
        //given
        StompEndpointRegistry registry = mock(StompEndpointRegistry.class);
        StompWebSocketEndpointRegistration registration = mock(StompWebSocketEndpointRegistration.class);
        when(registry.addEndpoint(Mockito.anyString())).thenReturn(registration);

        //when
        underTest.registerStompEndpoints(registry);

        //then
        verify(registry, times(1)).addEndpoint(Mockito.anyString());
        verify(registration, times(1)).setAllowedOrigins(Mockito.anyString());
    }

    @Test
    public void configureMessageBrokerEnableBroker() {
        //given
        MessageBrokerRegistry registry = mock(MessageBrokerRegistry.class);

        //when
        underTest.configureMessageBroker(registry);

        //then
        verify(registry, times(1)).enableSimpleBroker(Mockito.anyString());
    }

}
