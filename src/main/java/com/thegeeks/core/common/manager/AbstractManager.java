package com.thegeeks.core.common.manager;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.google.common.collect.ImmutableList;
import com.thegeeks.core.common.command.AbstractCommand;
import com.thegeeks.core.common.dal.entity.AbstractEntity;
import com.thegeeks.core.common.dal.service.AbstractService;
import com.thegeeks.core.common.dto.AbstractDto;

import jakarta.annotation.Nonnull;

/**
 *
 * @author kwangbum.ha@gmail.com
 * @date 2024. 11. 22.
 *
 * @param <C>
 * @param <E>
 * @param <D>
 */
public abstract class AbstractManager<C extends AbstractCommand, E extends AbstractEntity, D extends AbstractDto<E>> {

	/**
	 *
	 * @param command
	 * @return
	 */
	public Optional<D> selectById(@Nonnull final long id) {

		return this.getService().selectById(id);
	}

	/**
	 *
	 * @param command
	 * @return
	 */
	public Optional<D> selectOne(@Nonnull final C command) {

		return this.getService().selectOne(this.toEntity(command));
	}

	/**
	 *
	 * @param ids
	 * @return
	 */
	public ImmutableList<D> selectAllByIds(@Nonnull final Set<Long> ids) {

		return this.getService().selectAllByIds(ids);
	}

	/**
	 *
	 * @param command
	 * @return
	 */
	public ImmutableList<D> selectAll(@Nonnull final C command) {

		return this.getService().selectAll(this.toEntity(command));
	}

	/**
	 *
	 * @param command
	 * @return
	 */
	public Page<D> pagination(@Nonnull final C command) {

		return this.getService().pagination(
				this.toEntity(command),
				PageRequest.of(
						command.getPage(),
						command.getSize(),
						Sort.by(command.getOrders())));
	}

	/**
	 *
	 * @param id
	 */
	public boolean exists(@Nonnull final E entity) {

		return this.getService().exists(entity);
	}

	/**
	 *
	 * @param command
	 * @return
	 */
	public Optional<D> insert(@Nonnull final C command) {

		return this.getService().insert(this.toEntity(command));
	}

	/**
	 *
	 * @param commands
	 * @return
	 */
	public Optional<ImmutableList<D>> insertAll(@Nonnull final List<C> commands) {

		return this.getService().insertAll(
				commands.stream().map(command -> this.toEntity(command)).toList());
	}

	/**
	 *
	 * @param command
	 */
	public void update(@Nonnull final C command) {

		this.getService().update(this.toEntity(command));
	}

	/**
	 *
	 * @param id
	 */
	public void updateAsDeleted(@Nonnull final Long id) {

		this.getService().updateAsDeleted(id);
	}

	/**
	 *
	 * @param id
	 */
	public void delete(@Nonnull final Long id) {

		this.getService().delete(id);
	}

	/**
	 *
	 * @return
	 */
	protected abstract AbstractService<E, D> getService();

	/**
	 *
	 * @param command
	 * @return
	 */
	protected abstract E toEntity(C command);
}
