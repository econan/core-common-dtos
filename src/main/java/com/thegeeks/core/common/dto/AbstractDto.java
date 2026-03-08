package com.thegeeks.core.common.dto;

import java.time.ZonedDateTime;

import javax.annotation.Nonnull;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.thegeeks.core.common.dal.entity.AbstractEntity;
import com.thegeeks.core.common.util.ZonedDateTimeSerializer;

import lombok.Getter;

/**
 *
 * @author kwangbum.ha@gmail.com
 * @date 2023. 5. 5.
 *
 */
@Getter
public abstract class AbstractDto<E extends AbstractEntity> {

	private final Long id;
	private final Integer status;

	@JsonSerialize(using = ZonedDateTimeSerializer.class)
	private final ZonedDateTime created;

	public AbstractDto(@Nonnull final E entity) {
		this.id = entity.getId();
		this.status = entity.getStatus();
		this.created = entity.getCreated();
	}
}
