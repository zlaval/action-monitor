package com.zlrx.actionmonitor.database.serializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.zlrx.actionmonitor.common.exception.TechnicalException;
import lombok.extern.slf4j.Slf4j;

import java.time.format.DateTimeFormatter;

@Slf4j
public class ObjectSerializer {

    private ObjectMapper objectMapper;

    public ObjectSerializer() {
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addSerializer(new LocalDateTimeSerializer(DateTimeFormatter.ISO_DATE_TIME));
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(javaTimeModule);
    }

    public <T> String serializeObject(T object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            log.error("Object serialization failed", e);
            throw new TechnicalException("Object serialization failed", e);
        }
    }

}
