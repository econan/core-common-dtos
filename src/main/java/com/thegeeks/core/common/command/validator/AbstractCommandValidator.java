package com.thegeeks.core.common.command.validator;

import java.lang.annotation.Annotation;
import java.util.List;

import com.google.common.collect.Lists;
import com.thegeeks.core.common.command.AbstractCommand;
import com.thegeeks.core.common.command.validation.ValidationConstants;
import com.thegeeks.core.common.command.validation.exception.CommandValidationException;
import com.thegeeks.core.common.command.validation.exception.ValidationError;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 *
 * @author kwangbum.ha@gmail.com
 * @date 2023. 5. 13.
 *
 */
public abstract class AbstractCommandValidator<A extends Annotation, C extends AbstractCommand>
		implements ConstraintValidator<A, C> {

	@Override
	public void initialize(final A constraintAnnotation) {

		ConstraintValidator.super.initialize(constraintAnnotation);
	}

	@Override
	public boolean isValid(final C command, final ConstraintValidatorContext context) {

		final List<ValidationError> errors = Lists.newArrayList();

		if (!errors.isEmpty()) {
			throw new CommandValidationException(
					errors, command, ValidationConstants.DEFAULT_ERROR_MESSAGES);
		}

		return true;
	}

	/**
	 *
	 * @param command
	 * @param errors
	 */
	public abstract void validateCommand(C command, List<ValidationError> errors);

}
