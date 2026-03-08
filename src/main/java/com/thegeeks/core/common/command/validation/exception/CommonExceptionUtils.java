package com.thegeeks.core.common.command.validation.exception;

import org.springframework.lang.NonNull;

import com.google.common.collect.Lists;
import com.thegeeks.core.common.command.AbstractCommand;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

/**
 *
 * @author kwangbum.ha@gmail.com
 * @date 2024. 11. 24.
 *
 */
public class CommonExceptionUtils {

	/**
	 *
	 * @param caller
	 * @param errorField
	 * @param errorCode
	 */
	public static final CommandValidationException generateCommandValidationException(
			@NonNull final Exception e,
			@NonNull final String caller,
			@NonNull final String errorField,
			@NonNull final String errorCode) {

		return new CommandValidationException(
				Lists.newArrayList(new ValidationError(errorField, errorCode)),
				null,
				caller);

	}

	/**
	 *
	 * @param caller
	 * @param errorField
	 * @param errorCode
	 */
	public static final CommandValidationException generateCommandValidationException(
			@Nonnull final String caller,
			@Nonnull final String errorField,
			@Nonnull final String errorCode) {

		return new CommandValidationException(
				Lists.newArrayList(new ValidationError(errorField, errorCode)),
				null,
				caller);

	}

	/**
	 *
	 * @param caller
	 * @param errorField
	 * @param errorCode
	 */
	public static final void throwCommandValidationException(
			@Nonnull final String caller,
			@Nonnull final String errorField,
			@Nonnull final String errorCode) {

		throw new CommandValidationException(
				Lists.newArrayList(new ValidationError(errorField, errorCode)),
				null,
				caller);

	}

	/**
	 *
	 * @param caller
	 * @param errorField
	 * @param errorCode
	 * @param command
	 */
	public static final void throwCommandValidationException(
			@Nonnull final String caller,
			@Nonnull final String errorField,
			@Nonnull final String errorCode,
			@Nullable final AbstractCommand command) {

		throw new CommandValidationException(
				Lists.newArrayList(new ValidationError(errorField, errorCode)),
				command,
				caller);

	}

	/**
	 *
	 * @param caller
	 * @param errorField
	 * @param errorCode
	 * @param command
	 */
	public static final CommandValidationException getCommandValidationException(
			@Nonnull final String caller,
			@Nonnull final String errorField,
			@Nonnull final String errorCode,
			@Nullable final AbstractCommand command) {

		return new CommandValidationException(
				Lists.newArrayList(new ValidationError(errorField, errorCode)),
				command,
				caller);

	}

	private CommonExceptionUtils () {

	}
}
