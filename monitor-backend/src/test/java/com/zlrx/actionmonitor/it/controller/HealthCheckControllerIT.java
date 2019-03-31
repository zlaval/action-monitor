package com.zlrx.actionmonitor.it.controller;

import com.zlrx.actionmonitor.controller.HealthCheckController;
import com.zlrx.actionmonitor.service.AmqHealthService;
import com.zlrx.actionmonitor.type.HealthType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(HealthCheckController.class)
public class HealthCheckControllerIT {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private AmqHealthService service;

    @Test
    public void checkHealthReturnMqDownWhenMqNotSetStatusToUp() throws Exception {
        mvc.perform(
                get("/api/v1/health")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.condition").value(HealthType.MQ_DOWN.toString()));
    }


}
