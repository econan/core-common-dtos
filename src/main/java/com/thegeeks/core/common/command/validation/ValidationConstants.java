package com.thegeeks.core.common.command.validation;

/**
 *
 * @author kwangbum.ha@gmail.com
 * @date 2023. 5. 5.
 *
 */
public class ValidationConstants {

	public static final String DEFAULT_ERROR_MESSAGES = "There are field errors.";

	public static final long INVALID_ID = 0L;
	public static final long MIN_ID = 1L;
	public static final int ACTIVE_STATUS = 1;
	public static final int DELETED_STATUS = 0;

	public class ErrorCode {

		public static final String REQUIRED = "error.required";
		public static final String NOT_FOUND = "error.notfound";
		public static final String EXISTING = "error.existing";

		public static final String INVALID = "error.invalid";
		public static final String INVALID_MIN = "error.invalid.min";
		public static final String INVALID_MAX = "error.invalid.max";

		public static final String UNKNOWN = "error.unknown";

	}
}
