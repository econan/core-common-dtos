package com.thegeeks.core.common.util;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import jakarta.annotation.Nonnull;

/**
 *
 * @author kwangbum.ha@gmail.com
 * @date 2023. 5. 6.
 *
 */
public class CommonDateTimeUtils {

	/* TODO: Should be configured value or parameter */
	public static final String DEFAULT_TIME_ZONE_ID = "Asia/Seoul";

	public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
	public static final String SHORT_DATE_FORMAT = "yyyy-MM-dd";
	public static final String ISO_8601_STRING = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS";
	public static final String ISO_8601_STRING_WITHOUT_MILLIS = "yyyy-MM-dd'T'HH:mm:ss.SSS";

	public static DateTimeFormatter ISO8601 =
			DateTimeFormatter.ofPattern(ISO_8601_STRING).withZone(ZoneId.of(DEFAULT_TIME_ZONE_ID));
	public static DateTimeFormatter ISO8601_WITHOUT_MILLIS = DateTimeFormatter
			.ofPattern(ISO_8601_STRING_WITHOUT_MILLIS).withZone(ZoneId.of(DEFAULT_TIME_ZONE_ID));

	/**
	 *
	 * @return
	 */
	public static ZonedDateTime now() {

		return ZonedDateTime.now(ZoneId.of(DEFAULT_TIME_ZONE_ID));
	}

	/**
	 *
	 * @param zonedIdString
	 * @return
	 */
	public static ZonedDateTime now(@Nonnull final String zonedIdString) {

		return ZonedDateTime.now(ZoneId.of(DEFAULT_TIME_ZONE_ID));
	}

	/**
	 *
	 * @param shortDateString
	 * @param dateFormat
	 * @return
	 */
	public static ZonedDateTime fromDateString(@Nonnull final String shortDateString,
			@Nonnull final String dateFormat) {

		return ZonedDateTime.parse(shortDateString, DateTimeFormatter.ofPattern(SHORT_DATE_FORMAT)
				.withZone(ZoneId.of(DEFAULT_TIME_ZONE_ID)));
	}

	/**
	 *
	 * @param shortDateString
	 * @param dateFormat
	 * @param zonedIdString
	 * @return
	 */
	public static ZonedDateTime fromDateString(@Nonnull final String shortDateString,
			@Nonnull final String dateFormat, @Nonnull final String zonedIdString) {

		return ZonedDateTime.parse(shortDateString,
				DateTimeFormatter.ofPattern(dateFormat).withZone(ZoneId.of(zonedIdString)));
	}

	/**
	 *
	 * @param zonedDateTime
	 * @return
	 */
	public static long toMillis(@Nonnull final ZonedDateTime zonedDateTime) {

		return zonedDateTime.toInstant().toEpochMilli();
	}

	/**
	 *
	 * @param zonedDateTime
	 * @param format
	 * @return
	 */
	public static String toString(@Nonnull final ZonedDateTime zonedDateTime,
			@Nonnull final String format) {

		return zonedDateTime.format(DateTimeFormatter.ofPattern(format));
	}

	public static ZonedDateTime fromIso8601String(@Nonnull final String iso8601String) {

		try {
			return ZonedDateTime.parse(iso8601String, ISO8601);
		} catch (Exception e) {

			return ZonedDateTime.parse(iso8601String, ISO8601_WITHOUT_MILLIS);
		}
	}

	private CommonDateTimeUtils() {

	}
}
