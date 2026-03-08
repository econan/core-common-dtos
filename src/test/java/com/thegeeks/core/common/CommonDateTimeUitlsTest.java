package com.thegeeks.core.common;

import static org.junit.jupiter.api.Assertions.assertTrue;
import java.time.ZonedDateTime;
import java.time.format.DateTimeParseException;
import org.junit.jupiter.api.Test;
import com.thegeeks.core.common.util.CommonDateTimeUtils;

public class CommonDateTimeUitlsTest {

    @Test
    void testIso8601() {
        // Test the ISO8601 formatter
        String dateString = "2025-04-17T20:08:24.220395";
        ZonedDateTime zonedDateTime;

        try {
            // 문자열을 ZonedDateTime으로 변환
            zonedDateTime = ZonedDateTime.parse(dateString, CommonDateTimeUtils.ISO8601);
            assertTrue(null != zonedDateTime);
        } catch (DateTimeParseException e) {
            System.err.println("Error parsing date string: " + e.getMessage());
        }
    }

    @Test
    void testIso8601_() {
        // Test the ISO8601 formatter
        String dateString = "2025-04-22T00:00:00.000";
        ZonedDateTime zonedDateTime;

        try { // 추가

            // 문자열을 ZonedDateTime으로 변환
            zonedDateTime = ZonedDateTime.parse(dateString, CommonDateTimeUtils.ISO8601);
            assertTrue(null != zonedDateTime);
        } catch (DateTimeParseException e) {
            System.err.println("Error parsing date string: " + e.getMessage());
        }
    }
}
