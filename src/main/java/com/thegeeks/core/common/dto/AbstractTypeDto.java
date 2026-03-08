package com.thegeeks.core.common.dto;

import com.thegeeks.core.common.dal.entity.AbstractTypeEntity;

import jakarta.annotation.Nonnull;
import lombok.Getter;

/**
 *
 * @author kwangbum.ha@gmail.com
 * @date 2024. 11. 22.
 *
 */
@Getter
public abstract class AbstractTypeDto extends AbstractDto<AbstractTypeEntity> {

	private final String name;
	private final String description;

	/**
	 *
	 * @param entity
	 */
	public AbstractTypeDto(@Nonnull final AbstractTypeEntity entity) {

		super(entity);

		this.name = entity.getName();
		this.description = entity.getDescription();
	}

}
