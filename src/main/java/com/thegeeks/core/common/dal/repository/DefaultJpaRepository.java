package com.thegeeks.core.common.dal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.thegeeks.core.common.dal.entity.AbstractEntity;

/**
 *
 * @author kwangbum.ha@gmail.com
 * @date 2025. 04. 04.
 *
 */
@NoRepositoryBean
public interface DefaultJpaRepository<E extends AbstractEntity> extends JpaRepository<E, Long> {

}
