package com.amat.useraccess.model.dto.base;

import com.amat.useraccess.utils.BeanUtils;
import com.amat.useraccess.utils.ReflectionUtils;

import java.lang.reflect.ParameterizedType;
import java.util.Objects;

public interface InputConverter<DOMAIN> {

    /**
     * Convert to object class
     * @return
     */
    default DOMAIN convertTo() {
        ParameterizedType currentType = parameterizedType();

        Objects.requireNonNull(currentType, "Can not fetch actual type, because parameterized type is null");

        Class<DOMAIN> domainClass = (Class<DOMAIN>) currentType.getActualTypeArguments()[0];

        return BeanUtils.transformFrom(this, domainClass);
    }

    /**
     * get parameterized type
     * @return
     */
    default  ParameterizedType parameterizedType() {
        return ReflectionUtils.getParameterizedType(InputConverter.class, this.getClass());
    }
}
