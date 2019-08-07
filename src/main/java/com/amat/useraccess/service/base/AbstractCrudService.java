package com.amat.useraccess.service.base;

import com.amat.useraccess.repository.base.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public abstract class AbstractCrudService<DOMAIN, ID> implements CrudService<DOMAIN, ID> {

    private final String domainName;

    private final BaseRepository<DOMAIN, ID> repository;

    protected AbstractCrudService(BaseRepository<DOMAIN, ID> repository) {
        this.repository = repository;

        Class<DOMAIN> domainClass = (Class<DOMAIN>) fetchType(0);
        domainName = domainClass.getSimpleName();
    }

    /**
     * Get actual generic type
     * @param index generic type index
     * @return Type real generic type will be returned
     */
    private Type fetchType(int index) {
        Assert.isTrue(index>=0&&index<=1, "type index must between 0 and 1");
        return ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[index];
    }

    /**
     * List all
     * @return List
     */
    @Override
    public List<DOMAIN> listAll() {
        return repository.findAll();
    }

    /**
     * List all by sort
     * @param sort sort
     * @return List
     */
    @Override
    public List<DOMAIN> listAll(Sort sort) {
        return repository.findAll(sort);
    }

    /**
     * List all by pageable
     * @param pageable pageable
     * @return Page
     */
    @Override
    public Page<DOMAIN> listAll(Pageable pageable) {
        Assert.notNull(pageable, "Pageable info must not be null");
        return repository.findAll(pageable);
    }

    /**
     * List all by ids
     * @param ids ids
     * @return List
     */
    @Override
    public List<DOMAIN> listAllByIds(Collection<ID> ids) {
        return CollectionUtils.isEmpty(ids) ? Collections.emptyList() : repository.findAllById(ids);
    }

    /**
     * List all by ids of sort
     * @param ids  ids
     * @param sort sort
     * @return List
     */
    public List<DOMAIN> listAllByIds(Collection<ID> ids, Sort sort) {
        Assert.notNull(sort, "Sort info must not be null");
        return CollectionUtils.isEmpty(ids) ? Collections.emptyList() : repository.findAllByIdIn(ids, sort);
    }

    /**
     * Save by domain
     * @param domain domain
     * @return DOMAIN
     */
    public DOMAIN create(DOMAIN domain) {
        Assert.notNull(domain, domainName + " data must not be null");
        return repository.save(domain);
    }


}
