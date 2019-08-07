package com.amat.useraccess.utils;

import com.amat.useraccess.Exception.BeanUtilsException;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Set;

public class BeanUtils {

    public BeanUtils() {

    }

    /**
     * transferm from source object to target
     * @param source
     * @param targetClass
     * @param <T>
     * @return T
     */
    @Nullable
    public static <T> T transformFrom(@NonNull Object source, @NonNull Class<T> targetClass) {
        Assert.notNull(targetClass, "Target class must not be null");

        if(source == null) {
            return null;
        }

        try {
            T targetInstance = targetClass.newInstance();
            /**
             * public static void copyProperties(Object source, Object target, String... ignoreProperties)
             */
            org.springframework.beans.BeanUtils.copyProperties(source, targetInstance, getNullPropertyNames(source));
            return targetInstance;
        } catch (Exception e) {
            throw new BeanUtilsException("Failed to new " + targetClass.getName() + " instance or copy properties", e);
        }
    }

    /**
     * get null property names array
     * @param source
     * @return String[]
     */
    private static String[] getNullPropertyNames(@NonNull Object source) {
        return getNullPropertyNamesSet(source).toArray(new String[0]);
    }

    /**
     * get null property names set
     * @param source
     * @return Set
     */
    private static Set<String> getNullPropertyNamesSet(@NonNull Object source) {
        Assert.notNull(source, "source object must not be null");
        BeanWrapperImpl beanWrapper = new BeanWrapperImpl(source);
        PropertyDescriptor[] propertyDescriptors = beanWrapper.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<>();
        for(PropertyDescriptor propertyDescriptor:propertyDescriptors) {
            String propertyName = propertyDescriptor.getName();
            Object propertyValue = beanWrapper.getPropertyValue(propertyName);

            if(propertyValue == null) {
                emptyNames.add(propertyName);
            }
        }
        return emptyNames;
    }
}
