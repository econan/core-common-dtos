package com.thegeeks.core.common.util;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import jakarta.annotation.Nonnull;

/**
 *
 * @author kwangbum.ha@gmail.com
 * @date 2023. 5. 5.
 *
 */
public class ZonedDateTimeSerializer extends JsonSerializer<ZonedDateTime> {

	@Override
	public void serialize(
			@Nonnull final ZonedDateTime value,
			@Nonnull final JsonGenerator gen,
			final SerializerProvider serializers) throws IOException {

		gen.writeString(value.format(DateTimeFormatter.ISO_ZONED_DATE_TIME));
		// gen.writeNumber(CommonDateTimeUtils.toMillis(value));

	}

}
