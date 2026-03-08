package com.thegeeks.core.common.dal.entity;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author kwangbum.ha@gmail.com
 * @date 2023. 5. 6.
 *
 */
@MappedSuperclass
@Getter
@Setter
public abstract class AbstractTypeEntity extends AbstractEntity {

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "description")
	private String description;

	/**
	 * Default constructor
	 */
	public AbstractTypeEntity() {

	}

	/**
	 *
	 * @param name
	 * @param description
	 */
	public AbstractTypeEntity(
			@Nonnull final String name,
			@Nullable final String description) {

		super();
		this.name = name;
		this.description = description;
	}

	/**
	 *
	 * @param id
	 * @param status
	 * @param name
	 * @param description
	 */
	public AbstractTypeEntity(
			@Nonnull final Long id,
			@Nonnull final Integer status,
			@Nonnull final String name,
			@Nullable final String description) {

		super(id, status);
		this.name = name;
		this.description = description;
	}
}
