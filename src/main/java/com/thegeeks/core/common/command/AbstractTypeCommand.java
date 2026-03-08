package com.thegeeks.core.common.command;

import com.thegeeks.core.common.command.validation.ValidationConstants;
import com.thegeeks.core.common.command.validation.ValidationGroup;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author kwangbum.ha@gmail.com
 * @date 2023. 5. 7.
 *
 */
@Getter
@Setter
public abstract class AbstractTypeCommand extends AbstractCommand {

	@NotBlank(
			message = ValidationConstants.ErrorCode.REQUIRED,
			groups = {ValidationGroup.Insert.class, ValidationGroup.Update.class})
	private String name;

	@NotBlank(
			message = ValidationConstants.ErrorCode.REQUIRED,
			groups = {ValidationGroup.Insert.class, ValidationGroup.Update.class})
	private String description;
}
