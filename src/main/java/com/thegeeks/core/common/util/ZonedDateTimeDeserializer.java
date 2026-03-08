package com.thegeeks.core.common.util;

import java.io.IOException;
import java.time.ZonedDateTime;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class ZonedDateTimeDeserializer extends StdDeserializer<ZonedDateTime> {

    public ZonedDateTimeDeserializer() {
        super(ZonedDateTime.class);
    }

    @Override
    public ZonedDateTime deserialize(final JsonParser parser, final DeserializationContext ctxt)
            throws IOException, JsonProcessingException {
        return CommonDateTimeUtils.fromIso8601String(parser.getText());
    }

}
