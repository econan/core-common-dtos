package com.thegeeks.core.common.controller;

import com.thegeeks.core.common.command.AbstractTypeCommand;
import com.thegeeks.core.common.dal.entity.AbstractTypeEntity;
import com.thegeeks.core.common.dto.AbstractDto;
import com.thegeeks.core.common.dto.AbstractTypeDto;

/**
 *
 * @author kwangbum.ha@gmail.com
 * @date 2024. 11. 22.
 *
 * @param <C>
 * @param <E>
 * @param <D>
 */
public abstract class AbstractTypeController<C extends AbstractTypeCommand, E extends AbstractTypeEntity, D extends AbstractTypeDto>
		extends AbstractController<C, E, AbstractDto<E>> {

}
