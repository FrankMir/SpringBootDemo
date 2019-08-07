package com.amat.useraccess.utils;

import org.springframework.lang.NonNull;
import org.springframework.util.Assert;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class ReflectionUtils {

    private ReflectionUtils() {

    }

    /**
     * Get parameterized type
     * @param interfaceType  the interface type
     * @param implementionClass  the implement interface class
     * @return ParameterizedType
     */
    @NonNull
    public static ParameterizedType getParameterizedType(@NonNull Class<?> interfaceType, Class<?> implementionClass) {
        Assert.notNull(interfaceType, "Interface type must not be null");
        Assert.isTrue(interfaceType.isInterface(), "The given type must be an interface");

        if(implementionClass == null) {
            return null;
        }

        /**
         * If implements interfaces has interface type
         */
        ParameterizedType currentType = getParameterizedType(interfaceType, implementionClass.getGenericInterfaces());

        if(currentType != null) {
            return currentType;
        }

        /**
         * If implements class do not implement interfaces, get super class to get parameterized type
         */
        Class<?> superClass = implementionClass.getSuperclass();
        return  getParameterizedType(interfaceType, superClass);
    }

    /**
     * get parameterized type in implements interfaces type
     * @param superType
     * @param genericTypes
     * @return ParameterizedType
     */
    @NonNull
    public static ParameterizedType getParameterizedType(@NonNull Class<?> superType, Type... genericTypes) {
        Assert.notNull(superType, "Interface or super type must not be null");

        ParameterizedType currentType = null;

        for(Type genericType:genericTypes) {
            if(genericType instanceof ParameterizedType) {
                ParameterizedType parameterizedType = (ParameterizedType) genericType;
                if(parameterizedType.getRawType().getTypeName().equals(superType.getTypeName())) {
                    currentType = parameterizedType;
                    break;
                }
            }
        }
        return currentType;
    }
}
