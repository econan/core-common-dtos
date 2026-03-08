package com.thegeeks.core.common.util;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import com.fasterxml.jackson.databind.JsonSerializer;

public class ShortZonedDateTimeSerializer extends JsonSerializer<ZonedDateTime> {

    @Override
    public void serialize(final ZonedDateTime value,
            final com.fasterxml.jackson.core.JsonGenerator gen,
            final com.fasterxml.jackson.databind.SerializerProvider serializers)
            throws java.io.IOException {
        gen.writeString(
                value.format(DateTimeFormatter.ofPattern(CommonDateTimeUtils.SHORT_DATE_FORMAT)));
    }

}
