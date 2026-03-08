package com.thegeeks.core.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import jakarta.annotation.Nonnull;

/**
 *
 * @author kwangbum.ha@gmail.com
 * @date 2023. 5. 13.
 *
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidPathVariableException extends RuntimeException {

	private static final long serialVersionUID = 8271744284343966074L;

	/**
	 *
	 * @param pathVariable
	 * @param message
	 */
	public InvalidPathVariableException(
			@Nonnull final String pathVariable, @Nonnull final String message) {
		super(String.format("Failed for [%s]: %s", pathVariable, message));
	}
}
