package com.thegeeks.core.common.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.thegeeks.core.common.CommonMvcMapping;
import com.thegeeks.core.common.command.validation.ValidationConstants;
import com.thegeeks.core.common.command.validation.exception.CommandValidationException;
import com.thegeeks.core.common.command.validation.exception.ValidationError;

import jakarta.annotation.Nonnull;
import jakarta.validation.ValidationException;

/**
 *
 * @author kwangbum.ha@gmail.com
 * @date 2024. 12. 2.
 *
 */
public abstract class Controller {

	/**
	 * To handle custom validation exceptions.
	 */
	@ExceptionHandler(CommandValidationException.class)
	public ResponseEntity<ImmutableMap<String, List<ValidationError>>> handleCommandValidationException(
			@Nonnull final CommandValidationException e) {

		return new ResponseEntity<>(
				ImmutableMap.of(CommonMvcMapping.Keys.ERRORS, e.getFieldErrors()),
				HttpStatus.BAD_REQUEST);

	}

	/**
	 * To handle common validation exceptions.
	 */
	@ExceptionHandler(ValidationException.class)
	public ResponseEntity<ImmutableMap<String, List<ValidationError>>> handleValidationException(
			@Nonnull final ValidationException e) {

		return new ResponseEntity<>(
				ImmutableMap.of(
						CommonMvcMapping.Keys.ERRORS,
						((CommandValidationException) e.getCause()).getFieldErrors()),
				HttpStatus.BAD_REQUEST);

	}

	/**
	 * To handle a <code>MethodArgumentNotValidException</code> which is sent by
	 * spring @Valided annotation
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ImmutableMap<String, List<ValidationError>>> handleMethodArgumentNotValidException(
			@Nonnull final MethodArgumentNotValidException e) {

		final List<ValidationError> fieldErrors = Lists.newArrayList();
		e.getBindingResult().getFieldErrors().stream().forEach(error -> {
			fieldErrors.add(new ValidationError(error.getField(), error.getDefaultMessage()));
		});

		return new ResponseEntity<>(
				ImmutableMap.of(CommonMvcMapping.Keys.ERRORS, fieldErrors), HttpStatus.BAD_REQUEST);

	}

	/**
	 * To handle a <code>BindException</code.
	 */
	@ExceptionHandler(BindException.class)
	public ResponseEntity<ImmutableMap<String, List<ValidationError>>> handleMethodNotAllowedException(
			@Nonnull final BindException e) {

		final List<ValidationError> fieldErrors = Lists.newArrayList();
		e.getBindingResult().getFieldErrors().stream().forEach(error -> {
			fieldErrors.add(new ValidationError(error.getField(), error.getDefaultMessage()));
		});

		return new ResponseEntity<>(
				ImmutableMap.of(CommonMvcMapping.Keys.ERRORS, fieldErrors), HttpStatus.BAD_REQUEST);

	}

	/**
	 * To handle request parameter value that cannot deserialize
	 */
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<ImmutableMap<String, List<ValidationError>>> handleUnExpectedException(
			@Nonnull final HttpMessageNotReadableException e) {

		if (e.getCause() instanceof InvalidFormatException) {

			final List<ValidationError> fieldErrors = Lists.newArrayList();

			final InvalidFormatException cause = (InvalidFormatException)e.getCause();
			cause.getPath().forEach(reference -> {
				fieldErrors.add(new ValidationError(
						reference.getFieldName(),
						ValidationConstants.ErrorCode.INVALID));
			});
			return new ResponseEntity<>(ImmutableMap.of(CommonMvcMapping.Keys.ERRORS, fieldErrors), HttpStatus.BAD_REQUEST);

		}

		e.printStackTrace();

		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	/**
	 * To handle exception which is not catching anywhere.
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Void> handleException(@Nonnull final Exception e) {

		e.printStackTrace();

		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

	}
}
