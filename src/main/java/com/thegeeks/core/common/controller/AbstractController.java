package com.thegeeks.core.common.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.google.common.collect.Lists;
import com.thegeeks.core.common.CommonMvcMapping;
import com.thegeeks.core.common.command.AbstractCommand;
import com.thegeeks.core.common.command.validation.ValidationConstants;
import com.thegeeks.core.common.command.validation.ValidationGroup;
import com.thegeeks.core.common.command.validation.exception.CommandValidationException;
import com.thegeeks.core.common.command.validation.exception.ValidationError;
import com.thegeeks.core.common.dal.entity.AbstractEntity;
import com.thegeeks.core.common.dto.AbstractDto;
import com.thegeeks.core.common.manager.AbstractManager;

import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.Min;

/**
 *
 * @author kwangbum.ha@gmail.com
 * @date 2024. 11. 23.
 *
 * @param <C>
 * @param <E>
 * @param <D>
 */
public abstract class AbstractController<C extends AbstractCommand, E extends AbstractEntity, D extends AbstractDto<E>>
		extends Controller {

	/**
	 *
	 * @param id
	 * @param command
	 * @return
	 */
	@GetMapping(CommonMvcMapping.Url.PATH_ID)
	public ResponseEntity<D> selectById(
			@PathVariable(value = CommonMvcMapping.Keys.ID, required = true) @Min(value = ValidationConstants.MIN_ID, message = ValidationConstants.ErrorCode.INVALID_MIN) final Long id) {

		return ResponseEntity.ok(this.getManager().selectById(id).orElse(null));
	}

	/**
	 *
	 * @param command
	 * @return
	 */
	@GetMapping
	public ResponseEntity<Page<D>> pagination(
			@Validated({ ValidationGroup.Paging.class }) final C command) {

		return ResponseEntity.ok(this.getManager().pagination(command));
	}

	/**
	 *
	 * @param command
	 * @return
	 */
	@PostMapping
	public ResponseEntity<D> insert(
			@RequestBody @Validated({ ValidationGroup.Insert.class }) final C command) {

		return ResponseEntity.ok(this.getManager().insert(command).orElse(null));
	}

	/**
	 *
	 * @param id
	 * @param command
	 * @return
	 */
	@PutMapping(CommonMvcMapping.Url.PATH_ID)
	public ResponseEntity<Void> update(
			@PathVariable(value = CommonMvcMapping.Keys.ID, required = true) @Min(value = ValidationConstants.MIN_ID, message = ValidationConstants.ErrorCode.INVALID_MIN) final Long id,
			@RequestBody @Validated({ ValidationGroup.Update.class }) final C command) {

		// ... will throw an InvalidPathVariableException
		this.validatePathVariable(id, command);

		this.getManager().update(command);

		return new ResponseEntity<>(HttpStatus.OK);
	}

	/**
	 *
	 * @param id
	 * @param command
	 * @return
	 */
	@DeleteMapping(CommonMvcMapping.Url.PATH_ID)
	public ResponseEntity<Void> delete(
			@PathVariable(value = CommonMvcMapping.Keys.ID, required = true) @Min(value = ValidationConstants.MIN_ID, message = ValidationConstants.ErrorCode.INVALID_MIN) final Long id) {

		this.getManager().updateAsDeleted(id);

		return new ResponseEntity<>(HttpStatus.OK);
	}

	/**
	 * Override me to set manager for each controller.
	 *
	 * @return
	 */
	public abstract AbstractManager<C, E, D> getManager();

	/**
	 *
	 * @param id
	 * @param command
	 */
	private void validatePathVariable(@Nonnull final Long id, @Nonnull final C command) {

		if (id != command.getId()) {

			throw new CommandValidationException(
					Lists.newArrayList(
							new ValidationError(CommonMvcMapping.Keys.ID, ValidationConstants.ErrorCode.INVALID)),
					command,
					ValidationConstants.ErrorCode.INVALID);
		}
	}

}
