package com.thegeeks.core.common.command.validation.exception;

import java.util.List;

import javax.annotation.Nonnull;

import org.springframework.lang.NonNull;

import com.thegeeks.core.common.command.AbstractCommand;

import jakarta.annotation.Nullable;

/**
 *
 * @author kwangbum.ha@gmail.com
 * @date 2024. 11. 24.
 *
 */
public class CommandValidationException extends RuntimeException {

	static final long serialVersionUID = -1026035077444049085L;

	private final String errorMessage;

	private final List<ValidationError> fieldErrors;

	private final AbstractCommand command;

	/**
	 *
	 * @param fieldErrors
	 * @param command
	 * @param errorMessage
	 */
	public <C extends AbstractCommand> CommandValidationException(
			@NonNull final Exception e,
			@Nonnull final List<ValidationError> fieldErrors,
			@Nullable final C command,
			@Nonnull final String errorMessage) {

		super(e);
		this.fieldErrors = fieldErrors;
		this.command = command;
		this.errorMessage = errorMessage;
	}

	/**
	 *
	 * @param fieldErrors
	 * @param command
	 * @param errorMessage
	 */
	public <C extends AbstractCommand> CommandValidationException(
			@Nonnull final List<ValidationError> fieldErrors,
			@Nullable final C command,
			@Nonnull final String errorMessage) {

		super();
		this.fieldErrors = fieldErrors;
		this.command = command;
		this.errorMessage = errorMessage;
	}

	/**
	 *
	 * @param fieldErrors
	 * @param errorMessage
	 */
	public CommandValidationException(
			@Nonnull final List<ValidationError> fieldErrors,
			@Nonnull final String errorMessage) {

		super();
		this.fieldErrors = fieldErrors;
		this.command = null;
		this.errorMessage = null;
	}

	public List<ValidationError> getFieldErrors() {
		return this.fieldErrors;
	}

	public AbstractCommand getCommand() {
		return this.command;
	}

	public String getErrorMessage() {
		return this.errorMessage;
	}

}
