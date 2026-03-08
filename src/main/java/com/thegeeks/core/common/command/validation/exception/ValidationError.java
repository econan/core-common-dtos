package com.thegeeks.core.common.command.validation.exception;

import javax.annotation.Nonnull;

/**
 *
 * @author kwangbum.ha@gmail.com
 * @date 2023. 5. 6.
 *
 */
public class ValidationError {

	private final String field;
	private final String code;

	/**
	 *
	 * @param field
	 * @param code
	 */
	public ValidationError(@Nonnull final String field, @Nonnull final String code) {
		this.field = field;
		this.code = code;
	}

	public String getField() {
		return this.field;
	}

	public String getCode() {
		return this.code;
	}
}
