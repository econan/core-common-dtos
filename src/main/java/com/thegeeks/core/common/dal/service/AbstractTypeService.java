package com.thegeeks.core.common.dal.service;

import org.springframework.util.StringUtils;

import com.thegeeks.core.common.dal.entity.AbstractTypeEntity;
import com.thegeeks.core.common.dto.AbstractDto;
import com.thegeeks.core.common.dto.AbstractTypeDto;

import jakarta.annotation.Nonnull;

/**
 *
 * @author kwangbum.ha@gmail.com
 * @date 2023. 5. 7.
 *
 */
public abstract class AbstractTypeService<E extends AbstractTypeEntity, D extends AbstractTypeDto>
		extends AbstractService<E, AbstractDto<E>> {

	@Override
	public void toUpdate(@Nonnull final E selected, @Nonnull final E newEntity) {

		this.defaultUpdate(selected, newEntity);
	}

	/**
	 *
	 * @param selected
	 * @param newEntity
	 */
	protected void defaultUpdate(@Nonnull final E selected, @Nonnull final E newEntity) {

		if (StringUtils.hasText(newEntity.getName())) {
			selected.setName(newEntity.getName());
		}

		if (StringUtils.hasText(newEntity.getDescription())) {
			selected.setDescription(newEntity.getDescription());
		}
	}

}
