package com.amat.useraccess.repository.base;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.support.PageableExecutionUtils;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Slf4j
public class BaseRepositoryImpl<DOMAIN, ID> extends SimpleJpaRepository<DOMAIN, ID> implements BaseRepository<DOMAIN, ID> {

    private final JpaEntityInformation<DOMAIN, ID> entityInformation;

    private final EntityManager entityManager;

    public BaseRepositoryImpl(JpaEntityInformation<DOMAIN, ID> entityInformation,  EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityInformation = entityInformation;
        this.entityManager = entityManager;
    }

    @Override
    public List<DOMAIN> findAllByIdIn(Iterable<ID> ids, Sort sort) {
        Assert.notNull(ids,"The given Iterable of ids must not be null");
        Assert.notNull(sort,"Sort info must not be null");

        log.debug("Method findAllByIdIn has been invoked");

        if(!ids.iterator().hasNext()) {
            return Collections.emptyList();
        }

        if(!entityInformation.hasCompositeId()) {
            List<DOMAIN> results = new ArrayList<>();
            ids.forEach(id -> super.findById(id).ifPresent(results::add));
            return results;
        }

        ByIdsSpecification<DOMAIN> specification = new ByIdsSpecification<>(entityInformation);
        TypedQuery<DOMAIN> query = super.getQuery(specification, sort);
        return query.setParameter(specification.parameter, ids).getResultList();
    }

    @Override
    public Page<DOMAIN> findAllByIdIn(Iterable<ID> ids, Pageable pageable) {
        Assert.notNull(ids,"The given Iterable ids must not be null");
        Assert.notNull(pageable, "Page info must not be null");

        if(!ids.iterator().hasNext()) {
            return new PageImpl<>(Collections.emptyList());
        }

        if(entityInformation.hasCompositeId()) {
            throw new UnsupportedOperationException("Unsupported find all by composite id with page info");
        }

        ByIdsSpecification<DOMAIN> specification = new ByIdsSpecification<>(entityInformation);
        TypedQuery<DOMAIN> query = super.getQuery(specification, pageable).setParameter(specification.parameter, ids);
        TypedQuery<Long> countQuery = getCountQuery(specification, getDomainClass()).setParameter(specification.parameter, ids);

        return pageable.isUnpaged() ?
                new PageImpl<>(query.getResultList()):
                readPage(query, getDomainClass(), pageable, countQuery);
    }

    @Override
    public long deleteByIdIn(Iterable<ID> ids) {
        log.debug("Customized deleteByIdIn method was invoked");

        List<DOMAIN> domains = findAllById(ids);

        deleteInBatch(domains);

        return domains.size();
    }

    protected  <S extends DOMAIN> Page<S> readPage(TypedQuery<S> query, Class<S> domainClass, Pageable pageable, TypedQuery<Long> countQuery) {

        if(pageable.isPaged()) {
            query.setFirstResult((int) pageable.getOffset());
            query.setMaxResults(pageable.getPageSize());
        }

        return PageableExecutionUtils.getPage(query.getResultList(), pageable, ()->executeCountQuery(countQuery));
    }

    private static long executeCountQuery(TypedQuery<Long> countQuery) {
        Assert.notNull(countQuery, "TypedQuery must not be null");

        List<Long> totals = countQuery.getResultList();
        long total = 0L;

        for(Long element:totals) {
            total += element == null ? 0 : element;
        }

        return total;
    }

    private static final class ByIdsSpecification<T> implements Specification<T> {
        private static final long serialVersionUID = 1L;
        private final JpaEntityInformation<T, ?> entityInformation;

        @Nullable
        ParameterExpression<Iterable> parameter;

        ByIdsSpecification(JpaEntityInformation<T, ?> entityInformation) {
            this.entityInformation = entityInformation;
        }


        @Override
        public Predicate toPredicate(Root<T> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
            Path<?> path = root.get(this.entityInformation.getIdAttribute());
            this.parameter = cb.parameter(Iterable.class);
            return path.in(this.parameter);
        }
    }
}
