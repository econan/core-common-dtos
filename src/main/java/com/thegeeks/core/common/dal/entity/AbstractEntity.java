package com.thegeeks.core.common.dal.entity;

import java.time.ZonedDateTime;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 *
 * @author kwangbum.ha@gmail.com
 * @date 2023. 5. 5.
 *
 */
@MappedSuperclass
@Getter
@Setter
@SuperBuilder
public abstract class AbstractEntity {

	public static final int ALIVE = 1;
	public static final Long INVALID_ID = 0L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Integer status;

	private ZonedDateTime created;

	/**
	 * Default Constructor
	 */
	public AbstractEntity() {
		this.status = ALIVE;
	}

	/**
	 * 
	 * @param id
	 */
	public AbstractEntity(@Nonnull final Long id) {
		this.id = id;
		this.status = ALIVE;
	}

	/**
	 * Default Constructor
	 */
	public AbstractEntity(@Nonnull final Long id, @Nonnull final Integer status) {
		this.id = id;
		this.status = status;
	}

	/**
	 *
	 * @param id
	 * @param status
	 * @param created
	 */
	public AbstractEntity(
			final Long id,
			final Integer status,
			final ZonedDateTime created) {

		this.id = id;
		this.status = status;
		this.created = created;
	}
}
