package com.thegeeks.core.common.dal.service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.google.common.collect.ImmutableList;
import com.thegeeks.core.common.command.validation.ValidationConstants;
import com.thegeeks.core.common.dal.entity.AbstractEntity;
import com.thegeeks.core.common.dal.repository.DefaultJpaRepository;
import com.thegeeks.core.common.dto.AbstractDto;

import jakarta.annotation.Nonnull;

/**
 *
 * @author kwangbum.ha@gmail.com
 * @date 2023. 5. 5.
 *
 */
public abstract class AbstractService<E extends AbstractEntity, D extends AbstractDto<E>> {

	/**
	 *
	 * @param entity
	 * @return
	 */
	@Transactional(readOnly = true)
	public Optional<D> selectById(@Nonnull final long id) {

		final Optional<E> selected = this.getRepository().findById(id);
		if (selected.isEmpty()) {
			return Optional.empty();
		}

		return Optional.of(this.toDto(selected.get()));
	}

	/**
	 *
	 * @param entity
	 * @return
	 */
	@Transactional(readOnly = true)
	public Optional<D> selectOne(@Nonnull final E entity) {

		final Optional<E> selected = this.getRepository().findOne(Example.of(entity));
		if (selected.isEmpty()) {
			return Optional.empty();
		}

		return Optional.of(this.toDto(selected.get()));
	}

	/**
	 *
	 * @param entity
	 * @return
	 */
	@Transactional(readOnly = true)
	public ImmutableList<D> selectAllByIds(@Nonnull final Set<Long> ids) {

		final List<E> selected = this.getRepository().findAllById(ids);
		if (CollectionUtils.isEmpty(selected)) {
			return ImmutableList.of();
		}

		return ImmutableList.copyOf(this.toDtos(selected));
	}

	/**
	 *
	 * @param entity
	 * @return
	 */
	@Transactional(readOnly = true)
	public ImmutableList<D> selectAll(@Nonnull final E entity) {

		final List<E> selected = this.getRepository().findAll(Example.of(entity));
		if (CollectionUtils.isEmpty(selected)) {
			return ImmutableList.of();
		}

		return ImmutableList.copyOf(this.toDtos(selected));
	}

	/**
	 *
	 * @param entity
	 * @return
	 */
	@Transactional(readOnly = true)
	public Page<D> pagination(@Nonnull final E entity, @Nonnull final Pageable pageable) {

		return this.toPage(this.getRepository().findAll(Example.of(entity), pageable));
	}

	/**
	 * @param id
	 */
	@Transactional(readOnly = true)
	public boolean exists(@Nonnull final E entity) {

		return this.getRepository().exists(Example.of(entity));
	}

	/**
	 *
	 * @param entity
	 * @return
	 */
	@Transactional(readOnly = false)
	public Optional<D> insert(@Nonnull final E entity) {

		try {
			return Optional.ofNullable(toDto(this.getRepository().save(entity)));
		} catch (final Exception e) {
			// TODO: should throw a custom exception
			throw new RuntimeException(e);
		}

	}

	/**
	 *
	 * @param entity
	 * @return
	 */
	@Transactional(readOnly = false)
	public Optional<ImmutableList<D>> insertAll(@Nonnull final List<E> entities) {

		final List<E> inserted = this.getRepository().saveAll(entities);

		return CollectionUtils.isEmpty(inserted) ? Optional.of(this.toDtos(inserted)) : Optional.empty();
	}

	/**
	 *
	 * @param entity
	 * @return
	 */
	@Transactional(readOnly = false)
	public void update(@Nonnull final E entity) {

		final Optional<E> selected = this.getRepository().findById(entity.getId());
		if (selected.isEmpty()) {

			return;
		}

		this.toUpdate(selected.get(), entity);
		this.getRepository().save(selected.get());
	}

	/**
	 * NOTE: Update the status as deleted 0. Not delete a row actually.
	 *
	 * @param id
	 */
	@Transactional(readOnly = false)
	public void updateAsDeleted(@Nonnull final Long id) {

		final Optional<E> selected = this.getRepository().findById(id);
		if (selected.isEmpty()) {

			return;
		}

		final E entity = selected.get();
		entity.setStatus(ValidationConstants.DELETED_STATUS);

		this.getRepository().save(entity);
	}

	/**
	 * NOTE: Delete the row by given id. Please considering using
	 * this.updateAsDeleted() instead.
	 *
	 * @param id
	 */
	@Transactional(readOnly = false)
	public void delete(@Nonnull final Long id) {

		final Optional<E> selected = this.getRepository().findById(id);
		if (selected.isEmpty()) {

			return;
		}

		this.getRepository().deleteById(id);
	}

	/**
	 *
	 * @param selected
	 * @param newEntity
	 */
	public abstract void toUpdate(E selected, E newEntity);

	/**
	 *
	 * @return
	 */
	public abstract DefaultJpaRepository<E> getRepository();

	/**
	 *
	 * @param entity
	 * @return
	 */
	public abstract D toDto(E entity);

	/**
	 *
	 * @param page
	 * @return
	 */
	public Page<D> toPage(@Nonnull final Page<E> page) {

		return page.map(entity -> this.toDto(entity));
	}

	/**
	 *
	 * @param entities
	 * @return
	 */
	public ImmutableList<D> toDtos(@Nonnull final Collection<E> entities) {
		return ImmutableList.copyOf(
				entities.stream().map(entity -> this.toDto(entity)).toList());
	}
}
