package com.sky.nebula.carDealership.dto;


import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import io.cucumber.core.internal.com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;

public class MyDtoNullKeySerializer extends StdSerializer<Object> {
    public MyDtoNullKeySerializer() {
        this(null);
    }
    public MyDtoNullKeySerializer(Class<Object> t) {
        super(t);
    }

    @Override
    public void serialize(Object nullKey, JsonGenerator jsonGenerator, SerializerProvider unused)
            throws IOException, JsonProcessingException {
        jsonGenerator.writeFieldName("");
    }
}