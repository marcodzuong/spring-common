package com.bachdv.lib.common.bean;

import org.springframework.data.jpa.domain.Specification;

import java.util.Collection;

/**
 * @author bachdv
 */
public class DynamicSpecifications {
    public static <T> Specification<T> bySearchFilter(final Collection<SearchFilter> filters, final Class<T> entityClazz) {
        SimpleSpecification<T> simpleSpecification = new SimpleSpecification<T>(filters);
        return simpleSpecification;
    }
}
