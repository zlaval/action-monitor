package com.zlrx.actionmonitor.controller.config;

import org.junit.Test;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class SwaggerConfigurationTest {

    @Test
    public void createApiReturnDocket() {
        //given
        SwaggerConfiguration swaggerConfiguration = new SwaggerConfiguration();

        //when
        Docket docket = swaggerConfiguration.api();

        //then
        assertNotNull(docket);
        assertEquals(DocumentationType.SWAGGER_2, docket.getDocumentationType());
    }

}
