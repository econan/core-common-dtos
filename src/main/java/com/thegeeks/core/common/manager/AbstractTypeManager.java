package com.thegeeks.core.common.manager;

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
public abstract class AbstractTypeManager<C extends AbstractTypeCommand, E extends AbstractTypeEntity, D extends AbstractTypeDto>
		extends AbstractManager<C, E, AbstractDto<E>> {

}
