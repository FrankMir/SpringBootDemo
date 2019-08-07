package com.amat.useraccess.repository.base;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.lang.NonNull;

import java.util.List;

/**
 * Base repository interface contains some common methods.
 *
 * @param <DOMAIN> doamin type
 * @param <ID>     id type
 * @author XS
 */
@NoRepositoryBean
public interface BaseRepository<DOMAIN, ID> extends JpaRepository<DOMAIN, ID> {

    /**
     * Find all domain by domain id list
     * @param ids    must not be null
     * @param sort   must not be null
     * @return a list of domain
     */
    @NonNull
    List<DOMAIN> findAllByIdIn(@NonNull Iterable<ID> ids, @NonNull Sort sort);

    /**
     * Find all domain by domain id list
     * @param ids        must not be null
     * @param pageable   must not be null
     * @return a page of domain
     */
    @NonNull
    Page<DOMAIN> findAllByIdIn(@NonNull Iterable<ID> ids, @NonNull Pageable pageable);

    /**
     * Delete by ids
     * @param ids   id list of domain must not be null
     * @return number of rows affected
     */
    @NonNull
    long deleteByIdIn(@NonNull Iterable<ID> ids);
}
